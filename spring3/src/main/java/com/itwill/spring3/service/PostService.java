package com.itwill.spring3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itwill.spring3.dto.PageDto;
import com.itwill.spring3.dto.PostCreateDto;
import com.itwill.spring3.dto.PostDetailDto;
import com.itwill.spring3.repository.post.Post;
import com.itwill.spring3.repository.post.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

	// 생성자를 사용한 의존성 주입
	private final PostRepository postRepository;
	
	public List<Post> readpage(int startnum){
		int realnum = startnum*10;
		List<Post> list = postRepository.findByOrderByIdDesc();
		List<Post> list2 = new ArrayList<>();
		if(realnum+10>=list.size()) {
			
			for(int i = realnum; i<list.size(); i++) {
				
				list2.add(list.get(i));
				
			}
			
		} else {
			
			
			for(int i = realnum; i<realnum+10; i++) {
				
				list2.add(list.get(i));
				
			}
			
		}
		return list2;
	}
	
	// DB Post Table에서 전체 검색한 결과를 리턴.
	public List<Post> read(){
		
		return postRepository.findByOrderByIdDesc();
		
	}

	
	// DB Post 테이블에 새로운 Entity 삽입(insert):
	public int create(PostCreateDto dto) {
		
		if(postRepository.save(dto.toEntity())!=null) {
			
			return 1;
			
		} else {
			
			return 0;
			
		}
		
	}


	public PostDetailDto readById(long id) {
		// TODO Auto-generated method stub
		Post post = postRepository.findById(id).orElseThrow();
		
		
		
		return PostDetailDto.fromEntity(post);
	}


	public int update(PostDetailDto dto) {
		// TODO Auto-generated method stub
		int result = 0;
		if(postRepository.saveAndFlush(dto.toEntity())!=null) {
			
			result = 1;
			
		}
		
		return result;
	}


	public void delete(long id) {
		
		
		
		postRepository.deleteById(id);
		
		
	}

	public List<PageDto> makebtn() {
		// TODO Auto-generated method stub
		List<Post> list = postRepository.findByOrderByIdDesc();
		
		List<PageDto> list2 = new ArrayList<>();
		
		for(int i = 1 ; i<=(list.size()/10)+1; i++) {
			
			list2.add(PageDto.builder().number(i).build());
			
		}
		
		return list2;
	}


	
	
	
}
