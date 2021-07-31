package org.corona.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.corona.domain.AreaVO;


@Mapper
public interface MsgMapper {

	// 지역 리스트
	public ArrayList<AreaVO> asarea() throws Exception;
}
