package org.corona.controller;


import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.corona.domain.AreaVO;
import org.corona.domain.DisasterVO;
import org.corona.domain.StateVO;
import org.corona.service.StateService;
import org.corona.service.asaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;



@Controller
@RequestMapping
@AllArgsConstructor
public class StateController {
	
	private StateService ss;
	private asaService as;
	
	
	@GetMapping("/")
	public String test(Model model) throws Exception {
		
		String td = ss.today();	// 기준일 (=종료일)
		String bd = ss.day(td);	// 기준일-10일 (=시작일)
		
		String checkCovid = ss.getCovidStateApi(bd, td);
		String checkArea = as.getAreaApi(td, td);
		if (checkCovid.equals("e") || checkArea.equals("e")) { // api error
			if (checkCovid.equals("e")) { System.out.println("checkCovid: " + checkCovid); }
			if (checkArea.equals("e")) { System.out.println("checkArea: " + checkArea); }
			model.addAttribute("state", ss.Crawler());
			model.addAttribute("e", "e");
			return "/layout/info";
		}
		
		ArrayList<StateVO> slist = ss.covidState(checkCovid);
		ArrayList<StateVO> alist = ss.aCovidState(slist);
		model.addAttribute("alist", alist);
		ArrayList<AreaVO> arealist = as.asaArea(checkArea);
		model.addAttribute("arealist", arealist);
		
		return "/dailyAll/dashboard";
	}
	
	@GetMapping("/beta")
	public String beta(Model model) throws IOException, ParseException {
		
		model.addAttribute("state", ss.Crawler());
		
		ArrayList<DisasterVO> dlist = ss.DisasterMsg(ss.getDisasterMsgApi());
		if(dlist.size()==0) {
			model.addAttribute("n", "n");
		} else {
			model.addAttribute("dlist", dlist);
		}
		
		model.addAttribute("mCnt", ss.msgCount(dlist));
				
		return "/dailyAll/beta";
	}
	
}
