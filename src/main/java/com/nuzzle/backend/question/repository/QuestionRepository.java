package com.nuzzle.backend.question.repository;

import com.nuzzle.backend.question.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD

public interface QuestionRepository extends JpaRepository<Question, Long> {

    // 특정 질문에 대해 답변이 존재하는지 확인
    boolean existsByQuestionId(Long questionId);
=======
public interface QuestionRepository extends JpaRepository<Question, Long> {
>>>>>>> c9f35ada6c60f5934cc32d4d0762fd6c01719896
}
