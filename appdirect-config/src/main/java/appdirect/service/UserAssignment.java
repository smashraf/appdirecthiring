package appdirect.service;

import com.appdirect.appdirectobjects.AppdirectAPIResponse;
import com.appdirect.appdirectobjects.EventInfo;
import com.appdirect.appdirectobjects.type.ErrorCode;
import com.appdirect.entity.AppdirectUser;
import com.appdirect.entity.UserAccount;
import com.appdirect.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("UserAssignment")
public class UserAssignment implements EventHandler {

	private static final Logger logger = LoggerFactory.getLogger(UserAssignment.class);

	@Autowired
	private UserAccountService userAccountService;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public AppdirectAPIResponse handleEvent(EventInfo eventInfo) {

		String accountId = eventInfo.getPayload().getAccount().getAccountIdentifier();
		logger.info("Processing user assignment  for account: " + accountId);
		UserAccount account = null;
		try {
			account = userAccountService.getAccountById(accountId);
		} catch (ServiceException e) {
			return new AppdirectAPIResponse(false, "Account doesnot exists :" + accountId, ErrorCode.ACCOUNT_NOT_FOUND);
		}
		List<AppdirectUser> users = account.getAppdirectUser();

		String userUuid = eventInfo.getPayload().getUser().getUuid();

		for (AppdirectUser user : users) {
			if (user.getUuid().equalsIgnoreCase(userUuid)) {
				logger.debug("User Subscription Is Already There for user  :"+user.getUuid() );
				return new AppdirectAPIResponse(false, "User Subscription Is Already There", ErrorCode.USER_ALREADY_EXISTS);
			}
		}

		logger.info("Adding new user for account : " + accountId);
		AppdirectUser user = new AppdirectUser();
		user.setAdmin(false);
		user.setUuid(eventInfo.getPayload().getUser().getUuid());
		user.setFirstName(eventInfo.getPayload().getUser().getFirstName());
		user.setLastName(eventInfo.getPayload().getUser().getLastName());
		user.setEmail(eventInfo.getPayload().getUser().getEmail());
		user.setAccount(account);
		users.add(user);
		account.setAppdirectUser(users);
		userAccountService.saveAccount(account);
		return new AppdirectAPIResponse(true, "User added successfully to the account", null, accountId);

	}

}
