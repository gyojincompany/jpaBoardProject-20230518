package com.gyojincompany.board.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gyojincompany.board.entity.Answer;
import com.gyojincompany.board.entity.Question;
import com.gyojincompany.board.entity.SiteMember;
import com.gyojincompany.board.exception.DataNotFoundException;
import com.gyojincompany.board.repository.AnswerRepository;
import com.gyojincompany.board.repository.QuestionRepository;

@Service
public class AnswerService {
	
	@Autowired
	private AnswerRepository answerRepository;
	
	public void answerCreate(String content, Question question, SiteMember writer) {
		
		Answer answer = new Answer();
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());//서버의 현재시간 넣기
		answer.setQuestion(question);
		answer.setWriter(writer);
		
		answerRepository.save(answer);//insert 문
		
	}
	
	public Answer getAnswer(Integer id) {
		
		Optional<Answer> optAnswer = answerRepository.findById(id);
		
		if(optAnswer.isPresent()) {
			return optAnswer.get();//answer객체
		} else {
			throw new DataNotFoundException("선택하신 답변은 없는 글입니다.");
		}
	}
	
	public void answerModify(Answer answer, String content) {
		answer.setContent(content);
		answer.setModifyDate(LocalDateTime.now());//현재 시간 가져와 답변 수정시간으로 입력
		
		answerRepository.save(answer);
	}
}
