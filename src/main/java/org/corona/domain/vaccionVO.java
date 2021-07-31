package org.corona.domain;

import lombok.Data;


@Data
public class VaccionVO {
	
	private String orgcd;		// 기관코드
	private String orgnm;		// 기관명
	private String orgTlno;		// 기관전화번호
	private String orgZipaddr;	// 기관주소
	private String start;		// 주소
	private String slrYmd;		// 기준일자(현재날짜)
	private String dywk;		// 기준일자 요일
	private String hldyYn;		// 기준일자 휴무일여부
	private String lunchSttTm;	// 기준일자 점심시작시간
	private String lunchEndTm;	// 기준일자 점심종료시간
	private String sttTm;		// 기준일자 진료시작시간
	private String endTm;		// 기준일자 진료종료시간
	
}
