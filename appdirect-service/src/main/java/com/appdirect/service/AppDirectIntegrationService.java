package com.appdirect.service;

import com.appdirect.appdirectobjects.AppdirectAPIResponse;
import com.appdirect.appdirectobjects.EventInfo;
import org.springframework.stereotype.Service;


@Service
public interface AppDirectIntegrationService {
    public AppdirectAPIResponse handleEvent(EventInfo event);

}
