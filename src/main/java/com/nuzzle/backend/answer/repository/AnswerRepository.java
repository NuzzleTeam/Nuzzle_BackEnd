package com.nuzzle.backend.answer.repository;

import com.nuzzle.backend.answer.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
