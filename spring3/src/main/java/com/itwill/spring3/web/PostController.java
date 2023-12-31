package com.itwill.spring3.web;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.spring3.dto.PageDto;
import com.itwill.spring3.dto.PostCreateDto;
import com.itwill.spring3.dto.PostDetailDto;
import com.itwill.spring3.dto.PostSearchDto;
import com.itwill.spring3.repository.post.Post;
import com.itwill.spring3.repository.reply.Replies;
import com.itwill.spring3.repository.reply.ReplyRepository;
import com.itwill.spring3.service.PostService;
import com.itwill.spring3.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;
	
	private final ReplyService replyservice;
	
	// 페이지 목록 리스트
	@GetMapping("")
	public String post(Model model, @RequestParam int num) {
		
		log.info("post(num={})",num);
		
		if(num<=0) {
			
			num=0;
			
		}
		List<Post> postlist = postService.read();
		
		if(num>postlist.size()/10) {
			num = postlist.size()/10;
		}
		
		// 버튼 만드는 메서드
		List<PageDto> list2 = postService.makebtn(num,postlist);
		
		
		// 페이지에 표시할 게시글 리스트 만드는 메서드
		List<Post> list = postService.readpage(num,postlist);
		
		model.addAttribute("postlist",list);
		model.addAttribute("btnlist", list2);
		model.addAttribute("num",num);
		return "/post/read";
		
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/create")
	public void create() {
		
		log.info("create()");
		
		
		
	}
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/create")
	public String create(PostCreateDto dto) {
		
		log.info("create({})",dto);
		
		int result =postService.create(dto);
	
		log.info("Rows = {}",result);
		
		// DB 테이블 Insert 후 포스트 목록 페이지로 redirect 이동
		return "redirect:/post?num=0";
		
	}
	@PreAuthorize("hasRole('USER')")
	@GetMapping({"/details", "/modify"})
	public void details(@RequestParam long id, Model model) {
		
		log.info("id={}",id);
		
		PostDetailDto dto = postService.readById(id);
		
		List<Replies> list = replyservice.read(dto.toEntity());
		long count = replyservice.countByPost(dto.toEntity());
		model.addAttribute("detail", dto);
		model.addAttribute("replyCount",count);
		model.addAttribute("replylist",list);
	}
	
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/modify")
	public String modify(PostDetailDto dto) {
		log.info("modify({})",dto);
		
		int result = postService.update(dto);
		
		log.info("result update row count = {}", result);
		
		return "redirect:/post/details?id="+dto.getId(); 
	}
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/delete")
	public String delete(@RequestParam long id) {
		
		log.info("delete id={}",id);
		
		postService.delete(id);
		
		return "redirect:/post?num=0";
		
	}
	
	
	
	@GetMapping("/search")
	public String search(PostSearchDto dto, @RequestParam int num, Model model) {
		
		log.info("post(num={})", num);
		
		if(num<=0) {
			
			num=0;
			
		}
		
		List<Post> postlist = postService.findbyText(dto);
		
		if(num>postlist.size()/10) {
			num = postlist.size()/10;
		}
		
		// 버튼 만드는 메서드
		List<PageDto> list2 = postService.makebtn(num,postlist);
		
		
		// 페이지에 표시할 게시글 리스트 만드는 메서드
		List<Post> list = postService.readpage(num,postlist);
		
		model.addAttribute("postlist",list);
		model.addAttribute("btnlist", list2);
		model.addAttribute("num",num);
		model.addAttribute("dto",dto);
		model.addAttribute("dtoget",dto.getSearchtext());
		
		return "/post/search";
	}
	
}
