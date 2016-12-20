package appdirect.service;

import com.appdirect.appdirectobjects.AppdirectAPIResponse;
import com.appdirect.appdirectobjects.EventInfo;
import com.appdirect.appdirectobjects.OrderItem;
import com.appdirect.appdirectobjects.type.ErrorCode;
import com.appdirect.entity.OrderDetails;
import com.appdirect.entity.SubscriptionDetail;
import com.appdirect.entity.UserAccount;
import com.appdirect.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component("ChangeSubscription")
public class ChangeSubscription implements EventHandler {

	private static final Logger logger = LoggerFactory.getLogger(ChangeSubscription.class);

	@Autowired
	private UserAccountService userAccountService;


	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public AppdirectAPIResponse handleEvent(EventInfo eventInfo) {

		String accountId = eventInfo.getPayload().getAccount().getAccountIdentifier();
		logger.info("Processing subscription change event for account :" + accountId);
		UserAccount account = null;
		try {
			account = userAccountService.getAccountById(accountId);
		} catch (ServiceException e) {
			logger.debug("Account does not exists for account ID: " + accountId
			);
			return new AppdirectAPIResponse(false, "Account doesnot exists :" + accountId, ErrorCode.ACCOUNT_NOT_FOUND);
		}

		SubscriptionDetail order = new SubscriptionDetail();
		List<OrderDetails> lineItems = new ArrayList<>();


		order.setPricingDuration(eventInfo.getPayload().getOrder().getPricingDuration().name());
		order.setEditionCode(eventInfo.getPayload().getOrder().getEditionCode());
		for (OrderItem item : eventInfo.getPayload().getOrder().getItems()) {
			OrderDetails orderItemTosave = new OrderDetails();
			orderItemTosave.setUnit(item.getUnit().name());
			orderItemTosave.setQuantity(item.getQuantity());
			orderItemTosave.setOrder(order);
			lineItems.add(orderItemTosave);
		}

		// set order and order item
		List<SubscriptionDetail> orders = new ArrayList<>();

		order.setUserAccount(account);
		order.setOrderDetails(lineItems);
		orders.add(order);

		account.getOrder().clear();
		account.getOrder().addAll(orders);

		userAccountService.saveAccount(account);
		return new AppdirectAPIResponse(true, "Subscription changed for account ID: " + accountId, null, accountId);

	}
}


