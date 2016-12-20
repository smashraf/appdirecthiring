package com.appdirect.service;

import com.appdirect.appdirectobjects.AppdirectAPIResponse;
import com.appdirect.appdirectobjects.EventInfo;
import com.appdirect.appdirectobjects.OrderItem;
import com.appdirect.appdirectobjects.type.ErrorCode;
import com.appdirect.entity.AppdirectUser;
import com.appdirect.entity.OrderDetails;
import com.appdirect.entity.SubscriptionDetail;
import com.appdirect.entity.UserAccount;
import com.appdirect.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component("CreateSubscription")
public class CreateSubscription implements EventHandler {
    private static final Logger logger = LoggerFactory.getLogger(CreateSubscription.class);
    @Autowired
    private UserAccountService userAccountService;
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public AppdirectAPIResponse handleEvent(EventInfo eventInfo) {
        UserAccount userAccount = new UserAccount();
        userAccount.setAccountIdentifier(eventInfo.getPayload().getCompany().getUuid());
        try{
            UserAccount existingAccount = userAccountService.getAccountById(eventInfo.getPayload().getCompany().getUuid());
            logger.debug("Account exists with Id: " + existingAccount.getId());
            return new AppdirectAPIResponse(false,
                    "Account already exists for account ID: " + eventInfo.getPayload().getCompany().getUuid(),
                    ErrorCode.OPERATION_CANCELLED);
        }catch (ServiceException e){
            userAccount.setAppDirectBaseUrl(eventInfo.getMarketplace().getBaseUrl());
            AppdirectUser adminUser = new AppdirectUser();
            adminUser.setAdmin(true);


            SubscriptionDetail subscription= new SubscriptionDetail();

            List<OrderDetails> orders = new ArrayList<>();


            if (eventInfo.getPayload().getAccount() != null) {
                BeanUtils.copyProperties(userAccount, eventInfo.getPayload().getAccount());
            }
            adminUser.setEmail(eventInfo.getCreator().getEmail());
            adminUser.setFirstName(eventInfo.getCreator().getFirstName());
            adminUser.setLanguage(eventInfo.getCreator().getLastName());
            adminUser.setUuid(eventInfo.getCreator().getUuid());
            subscription.setEditionCode(eventInfo.getPayload().getOrder().getEditionCode());
            subscription.setPricingDuration(eventInfo.getPayload().getOrder().getPricingDuration().name());
            for (OrderItem item : eventInfo.getPayload().getOrder().getItems()) {
                OrderDetails orderItemTosave = new OrderDetails();
                orderItemTosave.setQuantity(item.getQuantity());
                orderItemTosave.setUnit(item.getUnit().name());
                orderItemTosave.setOrder(subscription);
                orders.add(orderItemTosave);
            }



            // set order and order item
            List<SubscriptionDetail> subscriptions = new ArrayList<>();

            subscription.setUserAccount(userAccount);
            subscription.setOrderDetails(orders);
            subscriptions.add(subscription);

            userAccount.setOrder(subscriptions);

            // set user
            List<AppdirectUser> users = new ArrayList<AppdirectUser>();
            adminUser.setAccount(userAccount);
            users.add(adminUser);
            userAccount.setAppdirectUser(users);

            UserAccount savedAccount = userAccountService.saveAccount(userAccount);

            return new AppdirectAPIResponse(true, "Account created successfully", null, savedAccount.getId());

        }



    }
}
