package com.nuzzle.backend.family.repository;

import com.nuzzle.backend.family.domain.mapping.FamilyQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyQuestionRepository extends JpaRepository<FamilyQuestion, Long> {
}
