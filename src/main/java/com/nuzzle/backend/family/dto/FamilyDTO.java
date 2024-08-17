package com.nuzzle.backend.family.dto;

import com.nuzzle.backend.pet.domain.PetColor;
import com.nuzzle.backend.pet.dto.PetDTO;
import com.nuzzle.backend.user.dto.UserDTO;
import lombok.Data;

import java.util.List;

@Data
public class FamilyDTO {
    private Long familyId;
    private String petName;
    private PetColor petColor;
    private String familyStatus;
    private String invitationCode;
    private PetDTO pet;
    private List<UserDTO> users;

    // CreateFamilyRequest와 JoinFamilyRequest를 포함한 내부 클래스를 추가
    @Data
    public static class CreateFamilyRequest {
        private Long userId;
    }

    @Data
    public static class JoinFamilyRequest {
        private Long userId;
        private String invitationCode;
    }

    @Data
    public static class LeaveFamilyRequest {
        private Long userId;
    }
}
