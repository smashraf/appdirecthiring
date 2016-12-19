package com.appdirect.config.util.oauthclient;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import java.io.IOException;

public interface SignedOauthClient {

	public String signAndGetObject(String eventUrl) throws IOException, OAuthMessageSignerException,
			OAuthExpectationFailedException, OAuthCommunicationException;
}
