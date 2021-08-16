package org.corona.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.corona.domain.GenAgeVO;
import org.corona.domain.AreaVO;
import org.corona.service.StateService;
import org.corona.service.asaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Controller
@AllArgsConstructor
@RequestMapping
public class AsaController {
	
	private asaService as;
	private StateService ss;
	
	
	@GetMapping("/asa")
	public String asaList(Model model) throws Exception {
		
		String checkArea = as.getAreaApi(as.today(), as.today());
		String checkGenAge = as.getGenAgeApi(as.today(), as.today());
		if (checkArea.equals("e") || checkGenAge.equals("e")) { // api error
			model.addAttribute("state", ss.Crawler());
			model.addAttribute("e", "e");
			return "/layout/info";
		}
		
		
		// 현재 시간 구하는 식
		SimpleDateFormat tt = new SimpleDateFormat("HHmmss");
		Date thisTime2 = new Date();
		String thisTime = tt.format(thisTime2);

		int thisTime1 = Integer.parseInt(thisTime);
		
		// 오전 12시 전이면 어제 날짜를 구하고
		if ((thisTime1 - 120000) < 0) {
			// 어제 날짜 구하는 식
			SimpleDateFormat yD = new SimpleDateFormat("yyyyMMdd");
			Date yDate = new Date();
			yDate = new Date(yDate.getTime() + (1000 * 60 * 60 * 24 * -1));
			// 어제 날짜의 데이터
			String startCreateDt = yD.format(yDate);
			String endCreateDt = yD.format(yDate);
			
			// 지역
			ArrayList<AreaVO> alist = as.asaArea(as.getAreaApi(startCreateDt, endCreateDt));
			model.addAttribute("alist", alist);
			// 성별연령
			ArrayList<GenAgeVO> blist = as.asaAge(as.getGenAgeApi(startCreateDt, endCreateDt));
			model.addAttribute("blist", blist);
			
		} 
		// 오전 12시 이후면 오늘 날짜를 구하고
		else {
			
			// 오늘 날짜 구하는 식
			SimpleDateFormat tD = new SimpleDateFormat("yyyyMMdd");
			Date time = new Date();
			// 오늘 날짜의 데이터
			String startCreateDt = tD.format(time);
			String endCreateDt = tD.format(time);
			
			// 지역
			ArrayList<AreaVO> alist = as.asaArea(as.getAreaApi(startCreateDt, endCreateDt));
			model.addAttribute("alist", alist);
			
			if (as.asaAge(as.getGenAgeApi(startCreateDt, endCreateDt)) == null) { // 오늘 날짜의 값이 널이면 어제 날짜를 구하고
				// 어제 날짜 구하는 식
				SimpleDateFormat yD = new SimpleDateFormat("yyyyMMdd");
				Date yDate = new Date();
				yDate = new Date(yDate.getTime() + (1000 * 60 * 60 * 24 * -1));

				// 어제 날짜의 데이터
				String sc = yD.format(yDate);
				String ec = yD.format(yDate);

				if (as.asaAge(as.getGenAgeApi(sc, ec)) == null) { // 오늘 날짜의 값이 널이어서 어제날짜를 구했는데 널이면 유감...
					String blist = "유감";
					model.addAttribute("blist", blist);
				} else { // 오늘 날짜의 값이 널이어서 어제날짜를 구했는데 널이아니면 어제 값을 넣어주고 리턴
					// 연령별, 성별
					ArrayList<GenAgeVO> blist = as.asaAge(as.getGenAgeApi(sc, ec));
					model.addAttribute("blist", blist);
				}
			} else { // 오늘날짜의 값이 널이 아니면 값을 넣어주고 리턴
				// 연령별, 성별
				ArrayList<GenAgeVO> blist = as.asaAge(as.getGenAgeApi(startCreateDt, endCreateDt));
				model.addAttribute("blist", blist);
			}
			
		}

		return "/ASA/asa";

	}
}
