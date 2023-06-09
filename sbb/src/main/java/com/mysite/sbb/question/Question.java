package com.mysite.sbb.question;



import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.mysite.sbb.answer.Answer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question {
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;  //글번호 @Id primary 키라는 뜻, @GeneratedValues~는 auto_increment
	
	@Column(length = 200)
	private String subject; //제목
	
	@Column(columnDefinition = "TEXT")
	private String content; //내용
	
	
	private LocalDateTime createDate; //작성일
	
	@OneToMany(mappedBy="question", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER )
	private List<Answer> answerList;
	
	
}
