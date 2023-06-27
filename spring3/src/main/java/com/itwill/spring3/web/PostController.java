package com.itwill.spring3.web;

import java.util.List;

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
import com.itwill.spring3.repository.post.Post;
import com.itwill.spring3.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;
	
	// 페이지 목록 리스트
	@GetMapping("")
	public String post(Model model, @RequestParam int num) {
		
		log.info("post(num={})",num);
		
		if(num<0) {
			
			num=0;
			
		}
		// 버튼 만드는 메서드
		List<PageDto> list2 = postService.makebtn();
		
		if(num>=list2.size()) {
			num = list2.size()-1;
		}
		// 페이지에 표시할 게시글 리스트 만드는 메서드
		List<Post> list = postService.readpage(num);
		
		model.addAttribute("postlist",list);
		model.addAttribute("btnlist", list2);
		model.addAttribute("num",num);
		return "/post/read";
		
	}
	
	
	@GetMapping("/create")
	public void create() {
		
		log.info("create()");
		
		
		
	}
	
	@PostMapping("/create")
	public String create(PostCreateDto dto) {
		
		log.info("create({})",dto);
		
		int result =postService.create(dto);
	
		log.info("Rows = {}",result);
		
		// DB 테이블 Insert 후 포스트 목록 페이지로 redirect 이동
		return "redirect:/post";
		
	}
	
	@GetMapping({"/details", "/modify"})
	public void details(@RequestParam long id, Model model) {
		
		PostDetailDto dto = postService.readById(id);
		model.addAttribute("detail", dto);
	}
	
	
	
	@PostMapping("/modify")
	public String modify(PostDetailDto dto) {
		log.info("modify({})",dto);
		
		int result = postService.update(dto);
		
		log.info("result update row count = {}", result);
		
		return "redirect:/post/details?id="+dto.getId(); 
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam long id) {
		
		log.info("delete id={}",id);
		
		postService.delete(id);
		
		return "redirect:/post";
		
	}
}
