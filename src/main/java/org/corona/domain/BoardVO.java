package org.corona.domain;

import lombok.Data;

@Data
public class BoardVO {
	
	private int b_num;			// 게시글 번호
	private String id;			// 아이디
	private String pw;			// 비밀번호
	private String b_title;		// 제목
	private String b_content;	// 내용
	private String b_time;		// 작성일시
	private int b_cnt;			// 조회수

}
