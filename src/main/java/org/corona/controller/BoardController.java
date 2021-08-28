package org.corona.controller;


import java.util.ArrayList;

import org.corona.domain.BoardVO;
import org.corona.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String write() {
		return "/board/write";
	}
	
}
