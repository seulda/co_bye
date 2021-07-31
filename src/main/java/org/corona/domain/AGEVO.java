package org.corona.domain;

import lombok.Data;


@Data
public class AgeVO {
	
	private String gubun;		// 연령, 성별
	private int confCase;		// 확진자
	private int confCaseRate;	// 확진률
	private int death;			// 사망자
	private float deathRate;	// 사망률
	
}
