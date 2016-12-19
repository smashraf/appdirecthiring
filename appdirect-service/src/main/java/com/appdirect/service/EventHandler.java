package com.appdirect.service;

import com.appdirect.appdirectobjects.AppdirectAPIResponse;
import com.appdirect.appdirectobjects.EventInfo;
import org.springframework.stereotype.Service;

/**
 * Created by covacsis on 19/12/16.
 */
@Service
public interface EventHandler {
    public AppdirectAPIResponse handleEvent(EventInfo eventInfo);
}
