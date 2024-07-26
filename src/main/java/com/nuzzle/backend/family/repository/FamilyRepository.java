package com.nuzzle.backend.family.repository;

import com.nuzzle.backend.family.domain.Family;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FamilyRepository extends JpaRepository<Family, Long> {
    // 초대 코드로 가족을 찾는 메서드
    Optional<Family> findByInvitationCode(String invitationCode);
}
