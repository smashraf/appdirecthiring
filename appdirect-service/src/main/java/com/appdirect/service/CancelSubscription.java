package com.appdirect.service;

import com.appdirect.appdirectobjects.AppdirectAPIResponse;
import com.appdirect.appdirectobjects.EventInfo;
import com.appdirect.appdirectobjects.type.ErrorCode;
import com.appdirect.entity.UserAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component("CancelSubscription")
public class CancelSubscription implements EventHandler {

	private static final Logger logger = LoggerFactory.getLogger(CancelSubscription.class);

	@Autowired
	private UserAccountService userAccountService;
	

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public AppdirectAPIResponse handleEvent(EventInfo eventInfo) {

		String accountId = eventInfo.getPayload().getAccount().getAccountIdentifier();
		logger.info("Processing subscription cancel event for account :" + accountId);
		UserAccount account = userAccountService.getAccountById(accountId);
		
		if(null==account){
			return new AppdirectAPIResponse(false, "Account doesnot exists :" + accountId, ErrorCode.ACCOUNT_NOT_FOUND);
		}

		userAccountService.deleteAccount(account);
		return new AppdirectAPIResponse(true, "Subscription cancelled successfully for account :" + accountId, null, accountId);
	}

}
