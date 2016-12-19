package com.appdirect.config;

/**
 * Created by covacsis on 16/12/16.
 */
public class ClientSecret {
    private String consumerKey;

    private String consumersecret;

    public ClientSecret(String consumerKey, String consumersecret) {
        this.consumerKey = consumerKey;
        this.consumersecret = consumersecret;
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public String getConsumersecret() {
        return consumersecret;
    }

    public void setConsumersecret(String consumersecret) {
        this.consumersecret = consumersecret;
    }
}
