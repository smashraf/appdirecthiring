package appdirect.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TestserviceImpl implements Testservice {
    private static final Logger logger = LogManager.getLogger(TestserviceImpl.class);
    @Value("${consumer.key}")
    private String consumerKey;

    @Value("${consumer.secret}")
    private String secret;

    @Value("${servicevalue}")
    private String testProperties;

    @Override
    public void testservice(int x) {
        logger.debug("hiiiii");
        System.out.println(secret);
        System.out.println(testProperties);
    }
}
