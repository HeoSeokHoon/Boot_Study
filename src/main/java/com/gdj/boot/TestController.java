package com.gdj.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.gdj.boot.ajax.RestTemplateTest;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TestController {
	
	
	@GetMapping("/")
	public String test()throws Exception {
		//trace<debug<info<warn<error
		log.error("error message");
		log.warn("warn message");
		log.info("info message");
		log.debug("debug message");
		log.trace("trace message");
		//restTemplateTest.flux();
		return "index";
	}
	
	@GetMapping("/expired")
	public String expierd(Model model) {
		
		model.addAttribute("result","logout");
		model.addAttribute("path","/member/login");
		
		
		return "commons/result";
	}
}
