package com.nuzzle.backend.question.repository;

import com.nuzzle.backend.question.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
