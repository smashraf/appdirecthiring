package com.appdirect.service;

import com.appdirect.appdirectobjects.AppdirectAPIResponse;
import com.appdirect.appdirectobjects.EventInfo;
import org.springframework.stereotype.Service;

/**
 * Created by covacsis on 18/12/16.
 */
@Service
public interface AppDirectIntegrationService {
    public AppdirectAPIResponse handleEvent(EventInfo event);

}
