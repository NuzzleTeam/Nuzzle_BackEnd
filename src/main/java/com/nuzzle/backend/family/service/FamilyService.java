package com.nuzzle.backend.family.service;

import com.nuzzle.backend.family.domain.Family;
import com.nuzzle.backend.family.dto.FamilyDTO;
import com.nuzzle.backend.user.domain.User;

import java.util.List;

public interface FamilyService {
    Family createFamily(User user); // 가족 생성 메서드
    FamilyDTO joinFamily(User user, String invitationCode); // 초대 코드로 가족에 합류하는 메서드
    void leaveFamily(User user); // 가족을 탈퇴하는 메서드
    Family getFamily(Long familyId); // 가족 정보를 가져오는 메서드
    List<User> getFamilyMembers(Long familyId); // 가족 구성원을 가져오는 메서드
    String getInvitationCode(Long familyId); // 가족 초대 코드를 가져오는 메서드

}
