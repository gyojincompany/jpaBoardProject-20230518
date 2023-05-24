package com.gyojincompany.board.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gyojincompany.board.dto.AnswerForm;
import com.gyojincompany.board.dto.MemberForm;
import com.gyojincompany.board.dto.QuestionForm;
import com.gyojincompany.board.entity.Question;
import com.gyojincompany.board.repository.QuestionRepository;
import com.gyojincompany.board.repository.SiteMemberRepository;
import com.gyojincompany.board.service.AnswerService;
import com.gyojincompany.board.service.MemberService;
import com.gyojincompany.board.service.QuestionService;

@Controller
public class BoardController {
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/")
	public String home() {
		return "redirect:questionList";
	}
	
	@RequestMapping(value = "/index")
	public String index() {
		return "redirect:questionList";
	}
	
	@RequestMapping(value = "/question_form")
	public String question_form() {
		return "question_form";
	}
	
	@PostMapping(value = "/questionCreate")//post만 받음
	public String create(Model model, @Valid QuestionForm questionForm, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {//에러가 발생하면 참
			
			return "question_form";
		} 
		
		questionService.questionCreate(questionForm.getSubject(), questionForm.getContent());
		
				
		return "redirect:questionList";
	}
	
	@GetMapping(value = "/questionCreate")
	public String questionCreate(QuestionForm questionForm) {
		
		return "question_form";
	}
	
	@RequestMapping(value = "/questionList")
	public String questionList(Model model) {
		
//		List<Question> questionList = questionRepository.findAll();
		//SELECT * FROM question
		
		List<Question> questionList = questionService.getQuestionList();
		
		model.addAttribute("questionList", questionList);
		
		return "question_list";
	}
	
	@RequestMapping(value = "/questionContentView/{id}")
	public String quesitonView(@PathVariable("id") Integer id, Model model, AnswerForm answerForm) {
		
//		System.out.print(id);//질문리스트에서 유저가 클릭한 글의 번호
		
		Question question = questionService.getQuestion(id);
		
		model.addAttribute("question", question);
		
		return "question_view";
	}
	
	@RequestMapping(value = "/answerCreate/{id}")
	public String answerCreate(Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerForm, BindingResult bindingResult) {		
		
		Question question = questionService.getQuestion(id);
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("question", question);
			
			return "question_view";
		}
		
		answerService.answerCreate(answerForm.getContent(), question);
		
		return String.format("redirect:/questionContentView/%s",id);		
	}
	
	@GetMapping(value = "/memberJoin")
	public String memberJoinForm(MemberForm memberForm) {
		return "member_join";
	}
	
	@PostMapping(value = "/memberJoin")
	public String memberJoin(@Valid MemberForm memberForm, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "member_join";
		}
		
		if(!memberForm.getUserpw1().equals(memberForm.getUserpw2())) {//비밀번호 확인 실패
			bindingResult.rejectValue("userpw2", "passwordCheckInCorrect","비밀번호 확인란의 비밀번호가 일치하지 않습니다.");
			return "member_join";
		}
		
		memberService.memberJoin(memberForm.getUserid(), memberForm.getUserpw1(), memberForm.getEmail());
		
		return "redirect:index";
	}
	
}
