package org.corona.controller;

import java.util.ArrayList;

import org.corona.domain.GenAgeVO;
import org.corona.domain.AreaVO;
import org.corona.service.StateService;
import org.corona.service.asaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;


@Controller
@AllArgsConstructor
@RequestMapping
public class AsaController {
	
	private asaService as;
	private StateService ss;
	
	
	@GetMapping("/asa")
	public String asaList(Model model) throws Exception {
		
		String td = as.today();
		String yd = as.today();
		String checkArea = as.getAreaApi(td, td);
		String checkGenAge = as.getGenAgeApi(yd, yd);
		if (checkArea.equals("e") || checkGenAge.equals("e")) { // api error
			model.addAttribute("state", ss.Crawler());
			model.addAttribute("e", "e");
			return "/layout/info";
		}
		if (checkGenAge.equals("n")) { // GenAgeApi null check 01
			yd = ss.yday(as.today());
		}
		
		// 지역
		ArrayList<AreaVO> area = as.asaArea(checkArea);
		model.addAttribute("area", area);
		
		// 성별연령
		if (checkGenAge.equals("n")) { // GenAgeApi null check 02
			model.addAttribute("genAge", "n");
		} else {
			ArrayList<GenAgeVO> genAge = as.asaGenAge(as.getGenAgeApi(yd, yd));
			model.addAttribute("genAge", genAge);
		}
		
		model.addAttribute("td", td);
		
		return "/ASA/asa";

	}
}
