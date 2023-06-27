package com.itwill.spring3.dto;

import com.itwill.spring3.repository.post.Post;

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
public class PostCreateDto {

	private String title;
	private String content;
	private String author;

	// DTO를 Entity 객체로 변환해서 리턴하는 메서드
	public Post toEntity() {
		
		return Post.builder().author(author).content(content).title(title).build();
		
	}
	
	
}
