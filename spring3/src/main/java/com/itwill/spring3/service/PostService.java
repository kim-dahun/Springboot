package com.itwill.spring3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.spring3.dto.PageDto;
import com.itwill.spring3.dto.PostCreateDto;
import com.itwill.spring3.dto.PostDetailDto;
import com.itwill.spring3.dto.PostSearchDto;
import com.itwill.spring3.dto.PostUpdateDto;
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
	
	@Transactional(readOnly = true)
	public List<Post> readpage(int startnum, List<Post> list){
		int realnum = startnum*10;
		log.info("realnum={}",realnum);
		
		List<Post> list2 = new ArrayList<>();
		if(realnum+10>=list.size()) {
			
			for(int i = realnum; i<list.size(); i++) {
				
				list2.add(list.get(i));
				log.info("add = {}",list.get(i));
			}
			
		} else {
			
			
			for(int i = realnum; i<realnum+10; i++) {
				
				list2.add(list.get(i));
				log.info("add = {}",list.get(i));
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

	
	@Transactional(readOnly = false) // (1)
	public int update(PostDetailDto dto) {
		
		// (1) 메서드에 @Transactional annotation을 설정하고,
		// (2) DB에서 Entity를 검색하고,
		// (3) 검색한 Entity를 수정하면,
		// 트랜잭션이 끝나는 시점에 DB Update가 자동으로 수행됨.
		Post entity = postRepository.findById(dto.getId()).orElseThrow(); // (2)
		entity.update(PostUpdateDto.builder().title(dto.getTitle()).content(dto.getContent()).build()); // (3)
		// 변수가 직접적으로 수정되어야 하고, 객체 자체가 바뀌는 경우에는 Transaction이 동작하지 않음.
		
		
		int result = 1;
//		if(postRepository.saveAndFlush(dto.toEntity())!=null) {
//			
//			result = 1;
//			
//		}
		
		return result;
	}


	public void delete(long id) {
		
		
		
		postRepository.deleteById(id);
		
		
	}

	public List<PageDto> makebtn(int num, List<Post> list) {
		// TODO Auto-generated method stub
		
		
		List<PageDto> list2 = new ArrayList<>();
		int x = (num/10)*10+1;
		if(x+10 > list.size()/10+1 && x <= list.size()/10+1) {
			
			for(int i = x ; i<=(list.size()/10)+1; i++) {
				
				list2.add(PageDto.builder().number(i).build());
				
			}
			
		} else if(x+10 <= list.size()/10+1 && x <= list.size()/10+1) {
				
				for(int i = x ; i<=x+9; i++) {
				
				list2.add(PageDto.builder().number(i).build());
				
			}
			
		} 
		
		
		
		return list2;
	}

	public List<Post> findbyText(PostSearchDto dto) {
		// TODO Auto-generated method stub
		int type = dto.getOptionlist();
		
		
		switch(type) {
		case 0:
			return postRepository.findByTitleContainsIgnoreCaseOrderByIdDesc(dto.getSearchtext());
		case 1:
			return postRepository.findByContentContainsIgnoreCaseOrderByIdDesc(dto.getSearchtext());
		case 2:
			return postRepository.selectAllJPQL(dto.getSearchtext());
		case 3:
			return postRepository.findByAuthorContainsIgnoreCaseOrderByIdDesc(dto.getSearchtext());
		default:
			return postRepository.findByTitleContainsIgnoreCaseOrderByIdDesc(dto.getSearchtext());
		}
		
		
	}


	
	
	
}
