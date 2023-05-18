package com.gyojincompany.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gyojincompany.board.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer>{

}
