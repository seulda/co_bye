package org.corona.service;

import java.util.ArrayList;

import org.corona.domain.GenAgeVO;
import org.corona.domain.AreaVO;

public interface asaService {
	
	// 날짜
	public String today();
	
	// 지역
	public String getAreaApi(String startCreateDt, String endCreateDt) throws Exception;
	public ArrayList<AreaVO> asaArea(String result);
	
	// 연령, 성별
	public String getGenAgeApi(String startCreateDt, String endCreateDt) throws Exception;
	public ArrayList<GenAgeVO> asaGenAge(String result);
	
}
