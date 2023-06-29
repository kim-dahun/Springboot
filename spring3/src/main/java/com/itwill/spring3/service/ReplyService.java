package com.itwill.spring3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.spring3.dto.ReplyCreateDto;
import com.itwill.spring3.dto.ReplyUpdateDto;
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
	
	public long countByPost(Post post) {
		
		log.info("countByPost({})",post);
		
		
		return replyrepository.countByPost(post);
	}

	public void writeReplyByPid(ReplyCreateDto dto, Post post) {
		// TODO Auto-generated method stub
		
		Replies reply = dto.toEntity(post);
		
		log.info("writeReplyByPid({})",reply);
		
		replyrepository.saveAndFlush(reply);
		
	}

	public void deleteReplyById(long replyid) {
		// TODO Auto-generated method stub
		
		replyrepository.deleteById(replyid); 
		
	}

	public Replies readById(long commentid) {
		// TODO Auto-generated method stub
		Replies reply = replyrepository.findById(commentid).orElseThrow();
		
		log.info("{}",reply);
		
		return reply;
	}

	@Transactional(readOnly = false)
	public Replies updateByDto(ReplyUpdateDto dto) {
		
		Replies reply = replyrepository.findById(dto.getId()).orElseThrow();
		
		reply.update(dto);
		
		return reply;
	}
	
}
