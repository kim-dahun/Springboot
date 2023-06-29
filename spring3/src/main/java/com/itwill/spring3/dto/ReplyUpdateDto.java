package com.itwill.spring3.dto;

import com.itwill.spring3.repository.reply.Replies;

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
public class ReplyUpdateDto {

	private long id;
	private String replyText;
	
	
	
}
