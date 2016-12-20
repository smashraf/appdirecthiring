package com.appdirect.unit.test.tests.integration

import com.appdirect.Application
import com.appdirect.appdirectobjects.EventInfo
import com.appdirect.service.AppDirectIntegrationService
import com.google.gson.Gson
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Specification
import spock.lang.Stepwise

@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes
= [Application.class])@WebAppConfiguration
@IntegrationTest("server.port:8000")
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Stepwise
class CreateSubscriptionSpec extends Specification {


	@Autowired
	private AppDirectIntegrationService appDirectIntegrationService;

	 def " should create subscription" (){
	 setup:
	
	 def jsonStr="{\"type\":\"SUBSCRIPTION_ORDER\",\"marketplace\":{\"partner\":\"APPDIRECT\",\"baseUrl\":\"https://marketplace.appdirect.com\"},\"applicationUuid\":null,\"flag\":\"DEVELOPMENT\",\"creator\":{\"uuid\":\"fb921d6b-4d71-4f62-a83c-b746d395c3a2\",\"openId\":\"https://marketplace.appdirect.com/openid/id/fb921d6b-4d71-4f62-a83c-b746d395c3a2\",\"email\":\"syedmeraj.ashraf@gmail.com\",\"firstName\":\"Meraj\",\"lastName\":\"Ashraf\",\"language\":\"en\",\"locale\":\"en-US\",\"address\":null,\"attributes\":null},\"payload\":{\"user\":null,\"company\":{\"uuid\":\"c1e6278f-45ea-4fdb-8f2e-453ee00ac6c4\",\"externalId\":null,\"name\":\"testcompany\",\"email\":null,\"phoneNumber\":\"8744844052\",\"website\":\"http://www.gmail.com\",\"country\":\"US\"},\"account\":null,\"addonInstance\":null,\"addonBinding\":null,\"order\":{\"editionCode\":\"FREE\",\"addonOfferingCode\":null,\"pricingDuration\":\"MONTHLY\",\"items\":[]},\"notice\":null,\"configuration\":{}},\"returnUrl\":null,\"links\":[]}"
	 def gson = new Gson()
	 def event = gson.fromJson(jsonStr, EventInfo.class)
	
	 when:
	
	 def apiResponse = appDirectIntegrationService.handleEvent(event)
	
	 then:
	 apiResponse.accountIdentifier != null
	 }
}
