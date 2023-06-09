package com.mysite.sbb.question;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.sbb.answer.AnswerForm;

import lombok.RequiredArgsConstructor;


@RequestMapping(value="/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

	
//		@Autowired
//		QuestionRepository questionRepository;
	
	
		public final QuestionService questionService; //서비스 역할을 담당하는 객체를 Di함
	
		@GetMapping("/list")
		public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
			//아래의 일을 service 클래스에게 넘겼다.
//			List<Question> questionList=this.questionRepository.findAll(); //select * from question;
			Page<Question> paging=this.questionService.getList(page);
			model.addAttribute("paging",paging); //modelandView랑 비슷
			return "question_list"; //템플릿 파일
		}
		
		@GetMapping(value="/detail/{id}")
		public String detail(@PathVariable("id") Integer id, Model model,AnswerForm answerForm) {
			Question question=this.questionService.getQuestion(id);
			model.addAttribute("question",question);
			return "question_detail";
			
		}
		
		@GetMapping("/create")
		public String questionCreate(QuestionForm questionForm) {
			return "question_form";
		}

		@PostMapping("/create")
		public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult ) {
			if(bindingResult.hasErrors()) {
				return "question_form";
			}
	        this.questionService.create(questionForm.getSubject(), questionForm.getContent());
	        return "redirect:/question/list";
	    }
		
		
}
		
	