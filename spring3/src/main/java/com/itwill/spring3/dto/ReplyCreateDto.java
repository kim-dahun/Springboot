package com.itwill.spring3.dto;

import org.springframework.beans.factory.annotation.Autowired;

import com.itwill.spring3.repository.post.Post;
import com.itwill.spring3.repository.reply.Replies;
import com.itwill.spring3.service.PostService;

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
public class ReplyCreateDto {
	
	private long postid;
	private String userid;
	private String content;
	
	public Replies toEntity(Post post) {
		
		
		return Replies.builder().writer(userid).replyText(content).post(post).build();
		
		
		
	}
	
}
