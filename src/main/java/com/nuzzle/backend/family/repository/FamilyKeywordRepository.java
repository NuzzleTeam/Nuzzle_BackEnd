package com.nuzzle.backend.family.repository;

import com.nuzzle.backend.family.domain.mapping.FamilyKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyKeywordRepository extends JpaRepository<FamilyKeyword, Long> {
}
