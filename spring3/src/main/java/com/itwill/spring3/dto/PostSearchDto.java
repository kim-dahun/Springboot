package com.itwill.spring3.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PostSearchDto {
	
	private int optionlist;
	private String searchtext;
	
	public void changeWord() {
		
		searchtext = "%"+searchtext+"%";
		
		//optionlist 0 => 제목, 1 => 내용, 2 => 제목+내용, 3 => 작성자
		
	}
	
}
