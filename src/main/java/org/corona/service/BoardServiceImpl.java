package org.corona.service;


import java.util.List;

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
	public List<BoardVO> list(BoardVO vo) {
		
		return null;
	}

	@Override
	public int check(String id, String pw, int b_num) {
		
		return 0;
	}

	@Override
	public BoardVO view(int b_num) {
		
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
