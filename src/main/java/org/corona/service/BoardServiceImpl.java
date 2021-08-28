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
	public int check(String id, String pw, int b_num) {
		
		return 0;
	}

	@Override
	public BoardVO read(int b_num) {
		
		return null;
	}

	@Override
	public void viewCount(int b_num) {
		
		
	}

	@Override
	public void create(int b_num) {
		
		
	}

	@Override
	public void update(int b_num) {
		
		
	}

	@Override
	public void delete(int b_num) {
		
		
	}

}
