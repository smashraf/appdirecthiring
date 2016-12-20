package com.appdirect.controller;

import com.appdirect.config.ClientSecret;
import com.appdirect.service.Testservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * testing Spring Boot
 */
@Controller
@RequestMapping("/api/v1")
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    private ClientSecret clientSecret;
    @Autowired
    private Testservice testservice;
    @RequestMapping(value="/hello",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String greeting() {
        logger.info("hey man it works");
        clientSecret.getConsumerKey();
        testservice.testservice(5);
        return "hello World";
    }
    @RequestMapping(value="/testTemplate")
    public String testTempalte(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

}
