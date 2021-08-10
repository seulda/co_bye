package org.corona.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {
	
//	@GetMapping("/")
//	public String index() {
//		return "/dailyAll/dashboard";
//	}
	
//	@GetMapping("/asa")
//	public String asa() {
//		return "/ASA/asa";
//	}

	@GetMapping("/vaccine/vac")
	public String vac() {
		return "/vaccine/vac";
	}
	
	@GetMapping("/hospital")
	public String hospital() {
		return "/hospital/hospital";
	}
	
}
