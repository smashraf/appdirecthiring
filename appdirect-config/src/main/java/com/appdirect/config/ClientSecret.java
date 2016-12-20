package com.appdirect.config;

/**
 * Prodcut Oauth Secret And key.
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
