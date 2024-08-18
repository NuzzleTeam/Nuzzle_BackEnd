package com.nuzzle.backend.family.repository;

import com.nuzzle.backend.family.domain.Family;
import com.nuzzle.backend.family.domain.mapping.FamilyKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FamilyKeywordRepository extends JpaRepository<FamilyKeyword, Long> {
    List<FamilyKeyword> findByFamily(Family family);

}
