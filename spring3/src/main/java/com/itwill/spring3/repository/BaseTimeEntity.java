package com.itwill.spring3.repository;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

// Table에서 공통으로 사용되는 생성시간, 수정시간을 Property로 갖는 객체를 설계:

@Getter
//-> getter 메서드 자동 생성.

@MappedSuperclass
//-> 다른 도메인(Entity) 클래스의 상위 클래스로 사용됨.
//-> 이 클래스를 상속하는 하위 클래스는 BaseTimeEntity가 정의하는 컬럼들을 갖게 됨. 

@EntityListeners(AuditingEntityListener.class)
//-> Main 메서드를 갖고 있는 메인 클래스에서 JPA Auditing 기능이 활성화되어있는 경우에
//-> Entity가 삽입/수정되는 시간이 자동으로 기록되도록 하기 위해서.
public class BaseTimeEntity {
	
	// Entity Class의 Field 이름은 데이터베이스 테이블의 컬럼 이름과 같거나,
	// 컬럼 이름을 Camel 표기법으로 변환한 이름으로 작성.
	// (예) 테이블 created_time - 클래스 createdTime
	
	@CreatedDate // insert 될 때의 시간이 자동으로 기록됨.
	private LocalDateTime createdTime;
	//-> Entity 클래스의 Field 이름은 자바의 관습(Camel) 으로 작성
	//-> 테이블의 컬럼명은 데이터베이스의 관습(snake 표기)을 따름
	
	@LastModifiedDate // update 될 때의 시간이 자동으로 기록됨.
	private LocalDateTime modifiedTime;
	
}
