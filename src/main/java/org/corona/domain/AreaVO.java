package org.corona.domain;

import lombok.Data;


@Data
public class AreaVO {
	
	private String stdDay; 		//기준일시
	private String createDt;	//등록일시
	private int deathCnt;		//사망자 수
	private int incDec;			//전일대비 증감 수
	private String gubun;		//시도명
	private int isolIngCnt;		//격리중인 환자 수
	private int isolClearCnt;	//격리해제 수
	private int defCnt;			//확진자 수
	private int overFlowCnt;	//해외유입 수
	private int localOccCnt;	//지역발생 수
	
}
