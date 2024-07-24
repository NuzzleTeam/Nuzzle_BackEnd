package com.nuzzle.backend.family.repository;

import com.nuzzle.backend.family.domain.Family;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRepository extends JpaRepository<Family, Long> {
}
