package com.appdirect.config.util.oauthclient;

import com.appdirect.config.ClientSecret;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.signature.QueryStringSigningStrategy;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Component
public class SignedOauthClientImpl implements SignedOauthClient {

	private static final Logger logger = LogManager.getLogger(SignedOauthClientImpl.class);
	@Autowired
	private ClientSecret appDirectClientSecret;

	@Override
	public String signAndGetObject(String eventUrl) throws IOException, OAuthMessageSignerException,
			OAuthExpectationFailedException, OAuthCommunicationException {
		System.out.println("Getting event details from appDirect server for eventURL: " + eventUrl);
		logger.info("Getting event details from appDirect server for eventURL: " + eventUrl);
		OAuthConsumer consumer = new DefaultOAuthConsumer(appDirectClientSecret.getConsumerKey(),
				appDirectClientSecret.getConsumersecret());

		consumer.setSigningStrategy(new QueryStringSigningStrategy());
		
		//sign URL with oauth. credentials are set from security config OAuthProviderProcessingFilter bean
		URL url = new URL(consumer.sign(eventUrl));

		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		
		//get json data only
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Accept", "application/json");
		System.out.println("Sending request... for event URL:" + eventUrl);
		logger.info("Sending request... for event URL:" + eventUrl);
		connection.connect();
		System.out.println("Response: " + connection.getResponseCode() + " " + connection.getResponseMessage()
				+ "received for event URL: " + eventUrl);
		logger.info("Response: " + connection.getResponseCode() + " " + connection.getResponseMessage()
				+ "received for event URL: " + eventUrl);
		
		//process stream to get JSON string
		BufferedReader br = null;
		if (200 <= connection.getResponseCode() && connection.getResponseCode() <= 299) {
			System.out.println("response success");
			br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
		} else {
			System.out.println("response eroor");
			br = new BufferedReader(new InputStreamReader((connection.getErrorStream())));
		}

		StringBuilder sb = new StringBuilder();

		String line;
		try {
			while ((line = br.readLine()) != null) {
				System.out.println("readline: " + line);
				sb.append(line);
			}

		} catch (IOException e) {
			System.out.println("Error occured while processing response for event URL: " + eventUrl);
			logger.debug("Error occured while processing response for event URL: " + eventUrl);
			throw new IOException("IO Exception while processing event for eventURL: " + eventUrl);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.debug("Error occured while processing bufferreader for event URL: " + eventUrl);
					throw new IOException("IO Exception while processing bufferreader for eventURL: " + eventUrl);
				}
			}
		}


		System.out.println(sb.toString());
		return sb.toString();

	}

}
