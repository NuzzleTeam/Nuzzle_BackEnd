package com.nuzzle.backend.question.repository;

import com.nuzzle.backend.question.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    // 특정 질문에 대해 답변이 존재하는지 확인
    boolean existsByQuestionId(Long questionId);
}
