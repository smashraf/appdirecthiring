package com.appdirect.controller;

import com.appdirect.appdirectobjects.AppdirectAPIResponse;
import com.appdirect.appdirectobjects.EventInfo;
import com.appdirect.appdirectobjects.type.ErrorCode;
import com.appdirect.config.util.oauthclient.SignedOauthClient;
import com.appdirect.service.AppDirectIntegrationService;
import com.google.gson.Gson;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Listner End Point For Appdirect
 */
@RestController
@RequestMapping("/api/addirectintegration/v1")
public class IntegrationController {

	private static final Logger logger =  LoggerFactory.getLogger(IntegrationController.class);

	@Autowired
	private SignedOauthClient signedOauthClient;

	@Autowired
	private AppDirectIntegrationService appDirectIntegrationService;


	@RequestMapping("/eventlistner")
	public AppdirectAPIResponse processEvent(HttpServletRequest request, HttpServletResponse response,
											 @RequestParam(value = "eventUrl", required = false) String eventUrl) {


		logger.info("evetURL from appdirect: " + eventUrl);

		String jsonResp = null;
		Gson gson = new Gson();
		try {
			//send a signed oauth request to get json payload from appdirect server
			jsonResp = signedOauthClient.signAndGetObject(eventUrl);
			logger.debug("JSON response for the event with eventURL: " + eventUrl + " JSON:" + jsonResp);
			logger.info("JSON response for the event with eventURL: " + eventUrl + " JSON:" + jsonResp);
		} catch (OAuthMessageSignerException | OAuthExpectationFailedException | OAuthCommunicationException
				| IOException e) {
			logger.debug("Error occured while processing event with event URL: " + eventUrl + " Error Details :"
					+ e.getMessage());
			return new AppdirectAPIResponse(false, "Error occured while fetch event details from appDirect server for event URL:"
					+ eventUrl + " Error message: " + e.getMessage(), ErrorCode.CONFIGURATION_ERROR);
		}
			logger.info("Sending JSON response to handlers for further processing :"+jsonResp);
		return appDirectIntegrationService.handleEvent(gson.fromJson(jsonResp, EventInfo.class));
		}

}
