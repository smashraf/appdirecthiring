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
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@RestController
@RequestMapping("/api/addirectintegration/v1")
public class IntegrationController {

	private static final Logger logger = LogManager.getLogger(IntegrationController.class);

	@Autowired
	private SignedOauthClient signedOauthClient;
//
	@Autowired
	private AppDirectIntegrationService appDirectIntegrationService;

	/**
	 * Single entry point for all incoming event notifications from appDirect
	 * API
	 *
	 * @param request
	 * @param response
	 * @param eventUrl
	 * @return
	 */

	@RequestMapping("/eventlistner")
	public AppdirectAPIResponse processEvent(HttpServletRequest request, HttpServletResponse response,
											 @RequestParam(value = "eventUrl", required = false) String eventUrl) {

		logger.info("Handling event with evetURL: " + eventUrl);
		System.out.println("API HIT SUCCESSS: "+ eventUrl);

//
		String jsonResp = null;
		Gson gson = new Gson();
		try {
//			//send a signed oauth request to get json payload from appdirect server
			jsonResp = signedOauthClient.signAndGetObject(eventUrl);
			System.out.println("JSON response for the event with eventURL: " + eventUrl + " JSON:" + jsonResp);
			logger.info("JSON response for the event with eventURL: " + eventUrl + " JSON:" + jsonResp);
		} catch (OAuthMessageSignerException | OAuthExpectationFailedException | OAuthCommunicationException
				| IOException e) {
			System.out.println("Error occured while processing event with event URL: " + eventUrl + " Error Details :"
					+ e.getMessage());
			logger.debug("Error occured while processing event with event URL: " + eventUrl + " Error Details :"
					+ e.getMessage());
			return new AppdirectAPIResponse(false, "Error occured while fetch event details from appDirect server for event URL:"
					+ eventUrl + " Error message: " + e.getMessage(), ErrorCode.CONFIGURATION_ERROR);
		}
//
		System.out.println("Sending JSON response to handlers for further processing....");
			logger.info("Sending JSON response to handlers for further processing....");
		System.out.println(gson.fromJson(jsonResp, EventInfo.class));
		return appDirectIntegrationService.handleEvent(gson.fromJson(jsonResp, EventInfo.class));
		}

}
