package com.itwill.spring3.repository.post;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.spring3.dto.PostUpdateDto;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class PostRepositoryTest {

	@Autowired
	private PostRepository postrepository;
	
//	@Test
	public void testSelectAll() {
		
		List<Post> list = postrepository.findByOrderByIdDesc();
		
		
		
		assertNotNull(list);
		for(Post x : list) {
			log.info("{}",x);
		}
		
	}
//	@Test
	public void test() {
		
		log.info("{}",(int)'a');
		
	}
	
	@Test
	public void testWrite() {
		
		
		for(int i = 0 ; i<300; i++) {
		Post post = Post.builder().title("테스트").author("나다").content("테스트내용").build();
		
		// DB 테이블에 Insert
		Post postsave = postrepository.save(post);
		//-> save 메서드는 테이블에 삽입할 Entity를 파라미터에 전달하면,
		// 테이블에 저장된 Entity 객체를 리턴.
		//-> Parameter에 전달된 Entity 필드들을 변경해서 Return.
		}
//		assertNotNull(postsave);
//		
//		log.info("{}",postsave);
//		log.info("crt = {}, mdt = {}",postsave.getCreatedTime(),postsave.getModifiedTime());
	}
	
//	@Test
	public void update() {
		
		Post post = Post.builder().title("테스트 수정").author("내가 아니다").content("테스트 수정내용").build();
		
		// 업데이트 전의 Entity를 검색
		Post prePost = postrepository.findById(62L).orElseThrow();
		log.info("update 전 = {}",prePost);
		log.info("update 전 mdt = {}",prePost.getModifiedTime());
		
		// Entity 객체 변경
		prePost.update(PostUpdateDto.builder().title("테스트 수정").content("테스트 수정한 내용").build());
		
		// DB 테이블 업데이트
		// JPA 에서는 insert와 update 메서드가 구분되어 있지 않음.
		// Save, SaveAndFlush 메서드의 Argument가 DB에 없는 Entity면 insert
		// DB에 있는 Entity면 Update 수행.
		postrepository.saveAndFlush(prePost);
		
		LinkedList<Integer> list = new LinkedList<>();
		Map<Integer, Integer> map = new TreeMap<>();
		
		
		
		list.clear();
		
	}
	
//	@Test
	public void delete() {
		
		long count = postrepository.count(); // DB 테이블의 삭제 전 행의 갯수
		
		log.info("삭제 전 행의 수 = {}",count);
		
		postrepository.deleteById(62L);
		
		count = postrepository.count();
		log.info("count = {}",count);
		
		
	}
	
	
}
