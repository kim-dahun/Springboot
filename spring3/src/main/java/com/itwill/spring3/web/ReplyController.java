package com.itwill.spring3.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.spring3.dto.ReplyCreateDto;
import com.itwill.spring3.dto.ReplyUpdateDto;
import com.itwill.spring3.repository.post.Post;
import com.itwill.spring3.repository.reply.Replies;
import com.itwill.spring3.service.PostService;
import com.itwill.spring3.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/reply")
public class ReplyController {

	private final ReplyService replyservice;
	private final PostService postservice;
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/count/{pid}")
	public ResponseEntity<Long> countReplyByPid(@PathVariable long pid){
		
		Post post = postservice.readById(pid).toEntity();
		
		long count = replyservice.countByPost(post);
		
		return ResponseEntity.ok(count);
		
	}
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/all/{pid}")
	public ResponseEntity<List<Replies>> loadRepliesByPid(@PathVariable long pid){
		
		Post post = postservice.readById(pid).toEntity();
		
		List<Replies> list = replyservice.read(post);
		
		return ResponseEntity.ok(list);
	}
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/write")
	public ResponseEntity<Integer> writeReplyByPid(@RequestBody ReplyCreateDto dto){
		
		Post post = postservice.readById(dto.getPostid()).toEntity();
		
		replyservice.writeReplyByPid(dto, post);
		
		return ResponseEntity.ok(1);
		
	}
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/delete/{replyid}")
	public ResponseEntity<Integer> deleteReplyById(@PathVariable long replyid){
		
		log.info("deleteReplyById({})",replyid);
		
		replyservice.deleteReplyById(replyid);
		
		return ResponseEntity.ok(1);
		
	}
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/find/{commentid}")
	public ResponseEntity<Replies> loadMyReply(@PathVariable long commentid){
		
		log.info("loadMyReply({})",commentid);
		
		Replies reply = replyservice.readById(commentid);
		
		return ResponseEntity.ok(reply);
	}
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/modify")
	public ResponseEntity<Replies> updateReply(@RequestBody ReplyUpdateDto dto){
		
		log.info("updateReply({})",dto);
		
		Replies reply = replyservice.updateByDto(dto);
		
		
		return ResponseEntity.ok(reply);
		
	}
	
}
