package org.corona.service;

import java.util.ArrayList;

import org.corona.domain.BoardVO;

public interface BoardService {
	
	// 게시글 목록
	public ArrayList<BoardVO> list();

	// 게시글 id/pw check
	public int check(String id, String pw, int b_num);
	
	// 게시글 보기
	public BoardVO view(int b_num);
	
	// 게시글 조회수
	public void viewCount(int b_num);
	
	// 게시글 작성
	public void create(int b_num);
	
	// 게시글 수정
	public void update(int b_num);
	
	// 게시글 삭제
	public void delete(int b_num);
	
}
