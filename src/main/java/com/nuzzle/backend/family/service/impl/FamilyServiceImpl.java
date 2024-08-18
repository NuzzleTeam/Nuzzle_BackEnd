package com.nuzzle.backend.family.service.impl;

import com.nuzzle.backend.family.domain.Family;
import com.nuzzle.backend.family.repository.FamilyRepository;
import com.nuzzle.backend.family.service.FamilyService;
import com.nuzzle.backend.user.domain.User;
import com.nuzzle.backend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FamilyServiceImpl implements FamilyService {
    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public Family createFamily(User user) {
        // 새로운 가족 생성
        Family family = new Family();
        family.setFamilyStatus("Active"); // 가족 상태 설정
        family.setInvitationCode(UUID.randomUUID().toString()); // 초대 코드 생성
        family = familyRepository.save(family);

        // 유저를 생성된 가족에 할당
        user.setFamily(family);
        userRepository.save(user);

        return family;
    }

    @Transactional
    @Override
    public Family joinFamily(User user, String invitationCode) {
        // 이미 가족이 있는 경우 예외 발생
        if (user.getFamily() != null) {
            throw new IllegalStateException("User is already in a family");
        }

        // 초대 코드로 가족 찾기
        Optional<Family> family = familyRepository.findByInvitationCode(invitationCode);
        if (!family.isPresent()) {
            throw new IllegalArgumentException("Invalid invitation code");
        }

        // 유저를 가족에 할당
        user.setFamily(family.get());
        userRepository.save(user);

        return family.get();
    }

    @Transactional
    @Override
    public void leaveFamily(User user) {
        // 유저가 가족에 속해 있지 않은 경우 예외 발생
        if (user.getFamily() == null) {
            throw new IllegalStateException("소속된 가족이 없어 탈퇴할 수 없습니다.");
        }

        // 유저를 가족에서 제거
        user.setFamily(null);
        userRepository.save(user);
    }

    @Override
    public Family getFamily(Long familyId) {
        // 가족 ID로 가족 정보 가져오기
        return familyRepository.findById(familyId).orElseThrow(() -> new IllegalArgumentException("Family not found"));
    }

    @Override
    public List<User> getFamilyMembers(Long familyId) {
        // 가족 구성원 리스트 가져오기
        Family family = getFamily(familyId);
        return family.getUsers();
    }

    @Override
    public String getInvitationCode(Long familyId) {
        // 가족 ID로 초대 코드 가져오기
        Family family = getFamily(familyId);
        return family.getInvitationCode();
    }
}
