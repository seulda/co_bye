package org.corona.controller;


import java.util.ArrayList;

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
	
	@GetMapping("/board")
	public String board(Model model) {
		ArrayList<BoardVO> list = bs.list();
		model.addAttribute("list", list);
		
		return "/board/board";
	}
	
	@GetMapping("/view")
	public String view() {
		return "/board/view";
	}
	
	@GetMapping("/new")
	public String writeNew() {
		return "/board/write";
	}
	
	@PostMapping("/write")
	@ResponseBody
	public String write(@RequestBody BoardVO bvo) {
		log.info("글 작성 요청 : " + bvo);
		int createState = bs.create(bvo);
		if (createState > 0) {
			log.info("글 작성 성공 : " + createState);
			return "success";
		}
		log.info("글 작성 실패 : " + createState);
		return "fail";
	}
	
}
