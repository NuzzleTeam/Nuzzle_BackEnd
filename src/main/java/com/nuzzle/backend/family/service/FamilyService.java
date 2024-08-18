package com.nuzzle.backend.family.service;


import com.nuzzle.backend.family.dto.FamilyDTO;
import com.nuzzle.backend.pet.domain.PetColor;
import com.nuzzle.backend.user.domain.User;

public interface FamilyService {
    FamilyDTO createFamily(User user); // 가족 생성 메서드
    FamilyDTO joinFamily(User user, String invitationCode); // 초대 코드로 가족에 합류하는 메서드
    void leaveFamily(User user); // 가족을 탈퇴하는 메서드
    FamilyDTO getFamily(Long familyId); // 가족 정보를 가져오는 메서드
    String getInvitationCode(Long familyId); // 가족 초대 코드를 가져오는 메서드
    FamilyDTO setPetName(Long familyId, String petName); // 펫 이름 설정 메서드
    FamilyDTO updatePetName(Long familyId, String petName); // 펫 이름 수정 메서드
    FamilyDTO setPetColor(Long familyId, PetColor petColor); // 펫 색상 설정 메서드
    FamilyDTO updatePetColor(Long familyId, PetColor petColor); // 펫 색상 수정 메서드

    FamilyDTO assignRandomPetToFamily(Long familyId); // 랜덤 펫을 가족에 할당

}
