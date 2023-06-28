package com.itwill.spring3.repository.replies;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itwill.spring3.repository.post.Post;
import com.itwill.spring3.repository.post.PostRepository;
import com.itwill.spring3.repository.reply.Replies;
import com.itwill.spring3.repository.reply.ReplyRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class ReplyRepositoryTest {

	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private PostRepository postRepository;
	
//	@Test
	public void testRead() {
		
		Replies reply = replyRepository.findById(2L).orElseThrow();
		
		log.info("{}",reply);
		
		// findById() 메서드는
		// Reply Entity 에서 FetchType.EAGER 를 사용한 경우에는 Join 문장을 실행.
		// FetchType.LAZY를 사용한 경우에는 단순 select 문장을 실행하고,
		// Post Entity가 필요한 경우 (나중에) Join 문장이 실행됨.
	}
	
	@Test
	public void testFindByPost() {
		
		Post post = postRepository.findById(1L).orElseThrow();
		
		List<Replies> list = replyRepository.findByPostOrderById(post);
		
		log.info(list.toString());
		
	}
	
}
