package org.corona.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.corona.domain.BoardVO;


@Mapper
public interface BoardMapper {
	
	// 게시글 상세보기
	public BoardVO view(int b_num);
	
	// 게시글 리스트
	public List<BoardVO> list(BoardVO vo);
	
}
