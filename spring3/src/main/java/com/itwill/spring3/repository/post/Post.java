package com.itwill.spring3.repository.post;

import com.itwill.spring3.repository.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@Entity // JPA Entity Class - DataBase Table 과 Mapping 되는 Class
@Table(name = "POST") // Entity Class Name 이 DataBase의 Table Name 과 다른경우 테이블 이름을 명시
@SequenceGenerator(name = "POST_SEQ_GEN", sequenceName = "POST_SEQ", allocationSize = 1)
public class Post extends BaseTimeEntity {
	
	@Id // primary key 제약조건
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_SEQ_GEN")
	private long id;
	
	@Column(nullable = false) // Not Null 제약조건
	private String title;
	
	@Column(nullable = false)
	private String content;
	
	@Column(nullable = false)
	private String author;
	
	
	
}
