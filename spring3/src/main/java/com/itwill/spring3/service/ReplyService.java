package com.itwill.spring3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.spring3.repository.post.Post;
import com.itwill.spring3.repository.reply.Replies;
import com.itwill.spring3.repository.reply.ReplyRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReplyService {

	@Autowired
	private ReplyRepository replyrepository;
	
	public List<Replies> read(Post post){
		
		log.info("read({})",post);
		
		List<Replies> list = replyrepository.findByPostOrderById(post);
		
		return list;
		
	}
	
	
	
}
