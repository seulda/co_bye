package org.corona.controller;


import org.corona.service.StateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;



@Controller
@RequestMapping
@AllArgsConstructor
public class HomeController {
	
	private StateService ss;
	
	
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
	
	@GetMapping("/info")
	public String info(Model model) {
		model.addAttribute("state", ss.Crawler());
		return "/layout/info";
	}
	
}
