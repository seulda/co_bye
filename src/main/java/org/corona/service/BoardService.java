package org.corona.service;

import java.util.ArrayList;

import org.corona.domain.BoardVO;

public interface BoardService {
	
	// 게시글 목록
	public ArrayList<BoardVO> list();

	// 게시글 id/pw check
	public int check(BoardVO bvo);
	
	// 게시글 보기
	public BoardVO read(BoardVO bvo);
	
	// 게시글 조회수
	public void viewCount(int b_num);
	
	// 게시글 작성
	public int create(BoardVO bvo);
	
	// 게시글 수정
	public int update(int b_num);
	
	// 게시글 삭제
	public int delete(int b_num);
	
}
