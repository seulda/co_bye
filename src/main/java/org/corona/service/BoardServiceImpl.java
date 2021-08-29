package org.corona.service;


import java.util.ArrayList;

import javax.inject.Inject;

import org.corona.domain.BoardVO;
import org.corona.mapper.BoardMapper;
import org.springframework.stereotype.Service;
import lombok.extern.log4j.Log4j;



@Service
@Log4j
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardMapper om;
	
	
	@Override
	public ArrayList<BoardVO> list() {
		ArrayList<BoardVO> list = om.list();
		return list;
	}

	@Override
	public int check(BoardVO bvo) {
//		String id = bvo.getId();
//		String pw = bvo.getPw();
//		int b_num = bvo.getB_num();
		
		System.out.println("serviceImpl check bvo: " + bvo);
		
		return om.check(bvo);
	}

	@Override
	public BoardVO read(BoardVO bvo) {
		return om.read(bvo);
	}

	@Override
	public void viewCount(int b_num) {
		
		
	}

	@Override
	public int create(BoardVO bvo) {
		return om.create(bvo);
	}

	@Override
	public int update(int b_num) {
		
		return 0;
	}

	@Override
	public int delete(int b_num) {
		
		return 0;
	}

}
