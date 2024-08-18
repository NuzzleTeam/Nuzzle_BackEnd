package com.nuzzle.backend.family.repository;

<<<<<<< HEAD
import com.nuzzle.backend.family.domain.Family;
import com.nuzzle.backend.family.domain.mapping.FamilyKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FamilyKeywordRepository extends JpaRepository<FamilyKeyword, Long> {
    List<FamilyKeyword> findByFamily(Family family);

=======
import com.nuzzle.backend.family.domain.mapping.FamilyKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyKeywordRepository extends JpaRepository<FamilyKeyword, Long> {
>>>>>>> 0fea6e0a7e3363f534cc21777b34438b51ee72c1
}
