package org.corona.service;

import java.util.ArrayList;

import org.corona.domain.AgeVO;
import org.corona.domain.AreaVO;

public interface asaService {
	
	// 날짜
	public String today();
	
	// 지역
	public String getAreaApi(String startCreateDt, String endCreateDt) throws Exception;
	public ArrayList<AreaVO> asaArea(String result);
	
	// 연령, 성별
	public String getAgeApi(String startCreateDt, String endCreateDt) throws Exception;
	public ArrayList<AgeVO> asaAge(String result);
	
}
