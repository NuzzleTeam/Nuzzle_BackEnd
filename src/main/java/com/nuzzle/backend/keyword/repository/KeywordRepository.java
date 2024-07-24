package com.nuzzle.backend.keyword.repository;

import com.nuzzle.backend.keyword.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
}
