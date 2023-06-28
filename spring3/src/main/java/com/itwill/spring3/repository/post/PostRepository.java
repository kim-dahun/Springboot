package com.itwill.spring3.repository.post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

	// ID 내림차순 정렬:
	// select * from post order by id desc
	List<Post> findByOrderByIdDesc();

	// select * from post p
	// where lower(p.title) like lower('%' || ? || '%')
	// order by p.id desc
	List<Post> findByTitleContainsIgnoreCaseOrderByIdDesc(String searchtext);

	// select * from post p
	// where lower(p.content) like lower('%' || ? || '%')
	// order by p.id desc
	List<Post> findByContentContainsIgnoreCaseOrderByIdDesc(String searchtext);

	
	// select * from post p
	// where lower(p.author) like lower('%' || ? || '%')
	// order by p.id desc
	List<Post> findByAuthorContainsIgnoreCaseOrderByIdDesc(String searchtext);
	
	// select * from post p
	// where lower(p.title) like lower('%' || ? || '%')
	// or lower(p.content) like lower('%' || ? || '%')
	// order by p.id desc
	// List<Post> findByTitleContainsIgnoreCaseOrByContentContainsIgnoreCaseOrderByIdDesc(String title, String content);
	
	// JPQL(JPA Query Language) 문법으로 쿼리를 작성하고, 그 쿼리를 실행하는 메서드 이름을 설정
	// JPQL은 Entity 클래스의 이름과 필드 이름들을 사용해서 작성.
	// (주의) DB 테이블의 이름과 컬럼 이름을 사용하지 않음.
	
	/* @query(
	*	"select p from Post p " +
	*	"where lower(p.title) like lower('%' || :keyword || '%') " +
	*	"or where lower(p.content) like lower('%' || :keyword || '%') " +
	*	"order by Id Desc"
	*
	*	List<Post> searchByKeyword(@param String keyword);
	*/
	@Query(value = "select * from Post Where Title Like > :searchtext Or Content Like > :searchtext Order By Id Desc", nativeQuery = true)
	public List<Post> selectAllJPQL(@Param(value="searchtext") String searchtext);
	
	
	
}
