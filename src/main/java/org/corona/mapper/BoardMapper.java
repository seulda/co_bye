package org.corona.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.corona.domain.BoardVO;


@Mapper
public interface BoardMapper {

	// 게시글 목록
	public ArrayList<BoardVO> list();

	// 게시글 id/pw check
	public int check(String id, String pw, int b_num);
	
	// 게시글 보기
	public BoardVO read(int b_num);
	
	// 게시글 조회수
	public void viewCount(int b_num);
	
	// 게시글 작성
	public int create(BoardVO bvo);
	
	// 게시글 수정
	public int update(int b_num);
	
	// 게시글 삭제
	public int delete(int b_num);
	
	
}
