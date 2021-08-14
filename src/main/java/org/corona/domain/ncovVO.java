package org.corona.domain;

import lombok.Data;

@Data
public class ncovVO {

	private String ndate;		// 기준 일자
	private String allCnt;		// 누적 확진자
	private String addCnt;		// 추가 확진자

}
