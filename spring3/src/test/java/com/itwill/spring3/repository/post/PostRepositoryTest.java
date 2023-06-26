package com.itwill.spring3.repository.post;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class PostRepositoryTest {

	@Autowired
	private PostRepository postrepository;
	
	@Test
	public void testSelectAll() {
		
		List<Post> list = postrepository.findByOrderByIdDesc();
		
		
		
		assertNotNull(list);
		for(Post x : list) {
			log.info("{}",x);
		}
		
	}
	
//	@Test
	public void testWrite() {
		
		
		
	}
	
}
