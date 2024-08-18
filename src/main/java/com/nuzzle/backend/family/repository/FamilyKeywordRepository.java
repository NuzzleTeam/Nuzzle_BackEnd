package com.nuzzle.backend.family.repository;

import com.nuzzle.backend.family.domain.Family;
import com.nuzzle.backend.family.domain.mapping.FamilyKeyword;
import com.nuzzle.backend.keyword.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FamilyKeywordRepository extends JpaRepository<FamilyKeyword, Long> {
    List<FamilyKeyword> findByFamily(Family family);
    Optional<FamilyKeyword> findByFamilyAndKeyword(Family family, Keyword keyword);
}
