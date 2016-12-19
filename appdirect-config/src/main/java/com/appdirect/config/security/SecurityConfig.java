package com.appdirect.config.security;

import com.appdirect.config.ClientSecret;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.consumer.BaseProtectedResourceDetails;
import org.springframework.security.oauth.consumer.ProtectedResourceDetails;
import org.springframework.security.oauth.provider.*;
import org.springframework.security.oauth.provider.filter.OAuthProviderProcessingFilter;
import org.springframework.security.oauth.provider.filter.ProtectedResourceProcessingFilter;
import org.springframework.security.oauth.provider.token.InMemoryProviderTokenServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger logger = LogManager.getLogger(SecurityConfig.class);


	@Autowired
	private ClientSecret clientSecret;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**");
	}

	/**
	 * Security configuration
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);

//		http.csrf().disable();

//		http.authorizeRequests().antMatchers("/","/api/v1/**").permitAll().anyRequest().authenticated();
//
//				.permitAll().defaultSuccessUrl("/apidetail");

//		http.addFilterBefore(oAuthProviderProcessingFilter(),BasicAuthenticationFilter.class);
	}

	/**
	 * Bean for checking signed response from appDirect. Its the intercepting filter that intercepts all incoming request for 
	 * URL patter /appdirect/ i,e, all process events for checking the request from actually came from appDirect
	 * @return
	 */
//	@Bean
//	public OAuthProviderProcessingFilter oAuthProviderProcessingFilter() {
//
//		final ProtectedResourceProcessingFilter filter = new ProtectedResourceProcessingFilter() {
//
//			@Override
//			protected boolean requiresAuthentication(final HttpServletRequest request,
//													 final HttpServletResponse response, final FilterChain filterChain) {
//
//				//TODO: remove this. logging sensitive info not a good idea!!
//				logger.info("Client security  Key:" + clientSecret.getConsumerKey() + " : " + "Secret"
//						+ clientSecret.getConsumersecret());
//
//				if (new AntPathRequestMatcher("/api/addirectintegration/**").matches(request)) {
//					logger.debug("URL pattern match the response received will pass through oAuth validation .....");
//					OAuthProcessingFilterEntryPoint authenticationEntryPoint = new OAuthProcessingFilterEntryPoint();
//					setAuthenticationEntryPoint(authenticationEntryPoint);
//					String realmName = request.getRequestURL().toString();
//					authenticationEntryPoint.setRealmName(realmName);
//					return true;
//				}
//				return false;
//			}
//		};
//		filter.setConsumerDetailsService(consumerDetailsService());
//		filter.setTokenServices(inMemoryProviderTokenServices());
//
//		return filter;
//	}
//
//	@Bean
//	public ConsumerDetailsService consumerDetailsService() {
//		final BaseConsumerDetails consumerDetails = new BaseConsumerDetails();
//		consumerDetails.setConsumerKey(clientSecret.getConsumerKey());
//		consumerDetails.setSignatureSecret(new SharedConsumerSecretImpl(clientSecret.getConsumersecret()));
//		consumerDetails.setRequiredToObtainAuthenticatedToken(false);
//
//		final InMemoryConsumerDetailsService consumerDetailsService = new InMemoryConsumerDetailsService();
//		consumerDetailsService.setConsumerDetailsStore(new HashMap<String, ConsumerDetails>() {
//			private static final long serialVersionUID = 5821471524901945830L;
//
//			{
//				put(clientSecret.getConsumerKey(), consumerDetails);
//			}
//		});
//		return consumerDetailsService;
//	}
//
//	@Bean
//	public InMemoryProviderTokenServices inMemoryProviderTokenServices() {
//		return new InMemoryProviderTokenServices();
//	}
//
//	@Bean
//	public ProtectedResourceDetails protectedResourceDetails() {
//		final BaseProtectedResourceDetails resource = new BaseProtectedResourceDetails();
//		resource.setConsumerKey(clientSecret.getConsumerKey());
//		resource.setSharedSecret(new SharedConsumerSecretImpl(clientSecret.getConsumerKey()));
//		return resource;
//	}

}