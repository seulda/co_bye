package org.corona.controller;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.corona.domain.BoardVO;
import org.corona.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;



@Controller
@RequestMapping
@AllArgsConstructor
@Log4j
public class BoardController {
	
	private BoardService bs;
	
	
	// board list
	@GetMapping("/board")
	public String board(Model model) {
		ArrayList<BoardVO> list = bs.list();
		model.addAttribute("list", list);
		
		return "/board/board";
	}
	
	// board write page
	@GetMapping("/new")
	public String writePage() {
		log.info("글 작성 page load");
		
		return "/board/write";
	}
	
	// board create
	@PostMapping("/write")
	@ResponseBody
	public String write(@RequestBody BoardVO bvo) {
		log.info("글 작성 요청 : " + bvo);
		int createState = bs.create(bvo);
		
		if (createState < 0) {
			log.info("글 작성 실패 : " + createState);
			
			return "fail";
		}
		
		log.info("글 작성 성공 : " + createState);
		
		return "success";
	}
	
	// board read request : ID/PW check
	@PostMapping("/check")
	@ResponseBody
	public String check(@RequestBody BoardVO bvo) {
		log.info("글 보기 요청 ID/PW 정보 : " + bvo);
		int check = bs.check(bvo);
		
		if (check < 0) {
			log.info("ID/PW 실패, check=" + check);
			
			return "fail";
		}
		
		log.info("ID/PW 성공, check=" + check);
		
		return "success";
	}
	
	// board read
	@PostMapping("/view")
	public String view(HttpServletRequest request, Model model) {
		BoardVO bvo = new BoardVO();
//		bvo.setId(request.getParameter("id"));
//		bvo.setPw(request.getParameter("pw"));
		bvo.setB_num(Integer.parseInt(request.getParameter("num")));
		log.info("글 보기 : " + bvo);
		
		BoardVO read = bs.read(bvo);
		model.addAttribute("read", read);
		
		return "/board/view";
	}
	
}
