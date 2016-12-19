package com.appdirect.service;

import com.appdirect.appdirectobjects.AppdirectAPIResponse;
import com.appdirect.appdirectobjects.EventInfo;
import com.appdirect.appdirectobjects.type.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by covacsis on 18/12/16.
 */
@Component
public class AppDirectIntegrationServiceImpl implements AppDirectIntegrationService{
   @Autowired
   @Qualifier("CreateSubscription")
   private EventHandler createSubscription;
    @Autowired
    @Qualifier("UserAssignment")
    private EventHandler userAssignment;
    @Autowired
    @Qualifier("ChangeSubscription")
    private EventHandler changeSubscription;

    @Autowired
    @Qualifier("CancelSubscription")
    private EventHandler cancelSubscription;

    @Override
    public AppdirectAPIResponse handleEvent(EventInfo event) {
        System.out.println(event);
        System.out.println("APIN HIT SERVICE");
        switch (event.getType()) {
            case SUBSCRIPTION_ORDER:
                return createSubscription.handleEvent(event);
            case USER_ASSIGNMENT:
                return userAssignment.handleEvent(event);
            case SUBSCRIPTION_CHANGE:
                return changeSubscription.handleEvent(event);
            case SUBSCRIPTION_CANCEL:
                return cancelSubscription.handleEvent(event);
            default:
                return new AppdirectAPIResponse(false,
                        "Event type not supported by this endpoint: " + String.valueOf(event.getType()),
                        ErrorCode.UNKNOWN_ERROR);
        }
    }
}
