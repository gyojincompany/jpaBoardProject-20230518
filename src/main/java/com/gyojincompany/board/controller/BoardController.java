package com.gyojincompany.board.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gyojincompany.board.entity.Question;
import com.gyojincompany.board.repository.QuestionRepository;

@Controller
public class BoardController {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@RequestMapping(value = "/index")
	public String index() {
		return "redirect:list";
	}
	
	@RequestMapping(value = "/question_form")
	public String question_form() {
		return "question_form";
	}
	
	@RequestMapping(value = "/questionCreate")
	public String create(HttpServletRequest request) {
		
		request.getParameter("subject");
		request.getParameter("content");
		
		Question question = new Question();
		question.setSubject(request.getParameter("subject"));
		question.setContent(request.getParameter("content"));
		question.setCreateDate(LocalDateTime.now());//서버의 현재시간 입력
		
		
		questionRepository.save(question);//insert(질문글 저장)
		
		return "redirect:list";
	}
	
	
	
}
