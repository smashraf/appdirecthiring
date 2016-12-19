package com.appdirect.service;

import com.appdirect.appdirectobjects.AppdirectAPIResponse;
import com.appdirect.appdirectobjects.EventInfo;
import com.appdirect.appdirectobjects.OrderItem;
import com.appdirect.appdirectobjects.type.ErrorCode;
import com.appdirect.entity.OrderDetails;
import com.appdirect.entity.SubscriptionDetail;
import com.appdirect.entity.UserAccount;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Component("ChangeSubscription")
public class ChangeSubscription implements EventHandler {

	private static final Logger logger = LogManager.getLogger(ChangeSubscription.class);

	@Autowired
	private UserAccountService userAccountService;
//

	/**
	 * Handle subscription change event
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public AppdirectAPIResponse handleEvent(EventInfo eventInfo) {

		String accountId = eventInfo.getPayload().getAccount().getAccountIdentifier();
		logger.info("Processing subscription change event for account :" + accountId);

		UserAccount account = userAccountService.getAccountById(accountId);

		if (null == account) {
			return new AppdirectAPIResponse(false, "Account does not exists for account ID: " + accountId, ErrorCode.ACCOUNT_NOT_FOUND);
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


