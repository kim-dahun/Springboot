package com.itwill.spring3.repository.reply;



import com.itwill.spring3.dto.ReplyUpdateDto;
import com.itwill.spring3.repository.BaseTimeEntity;
import com.itwill.spring3.repository.post.Post;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"post"})
@Entity
@Table(name = "replies")
@SequenceGenerator(name = "REPLIES_SEQ_GEN", sequenceName = "REPLIES_SEQ", allocationSize = 1)
public class Replies extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REPLIES_SEQ_GEN")
	private long id; // Primary key
	
	@ManyToOne(fetch = FetchType.EAGER) // EAGER(기본값) : 즉시로딩, LAZY: 지연로딩
	private Post post; // Foreign Key, 관계를 맺고 있는 Entity
	
	@Column(nullable = false)
	private String replyText;
	
	@Column(nullable = false)
	private String writer;
	
	public void update(ReplyUpdateDto dto) {
		
		replyText = dto.getReplyText();
		
		
	}
	
}
