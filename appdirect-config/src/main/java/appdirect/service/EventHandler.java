package appdirect.service;

import com.appdirect.appdirectobjects.AppdirectAPIResponse;
import com.appdirect.appdirectobjects.EventInfo;
import org.springframework.stereotype.Service;

/**
 * Handle Appdirect Trigerred Event
 */
@Service
public interface EventHandler {
    public AppdirectAPIResponse handleEvent(EventInfo eventInfo);
}
