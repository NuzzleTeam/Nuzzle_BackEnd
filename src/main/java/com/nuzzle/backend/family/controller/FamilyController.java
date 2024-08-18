package com.nuzzle.backend.family.controller;

import com.nuzzle.backend.family.dto.FamilyDTO;
import com.nuzzle.backend.family.service.FamilyService;
import com.nuzzle.backend.pet.domain.PetColor;
import com.nuzzle.backend.user.domain.User;
import com.nuzzle.backend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/family")
public class FamilyController {
    @Autowired
    private FamilyService familyService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createFamily(@RequestBody FamilyDTO.CreateFamilyRequest request) {
        // 유저 정보 가져오기
        User user = userService.getUserById(request.getUserId());

        // 응답 데이터 생성
        Map<String, Object> response = new HashMap<>();
        try {
            // 가족 생성
            FamilyDTO family = familyService.createFamily(user);
            response.put("family_id", family.getFamilyId());
            response.put("pet_name", family.getPetName()); // pet_name은 null일 수 있음
            response.put("pet_color", family.getPetColor()); // pet_color는 null일 수 있음
            response.put("invitation_code", family.getInvitationCode());
            response.put("family_status", family.getFamilyStatus());
            return ResponseEntity.ok(response);
        } catch (IllegalStateException e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

    @PostMapping("/join")
    public ResponseEntity<Map<String, Object>> joinFamily(@RequestBody FamilyDTO.JoinFamilyRequest request) {
        // 유저 정보 가져오기
        User user = userService.getUserById(request.getUserId());

        // 응답 데이터 생성
        Map<String, Object> response = new HashMap<>();
        try {
            // 초대 코드로 가족에 합류
            FamilyDTO family = familyService.joinFamily(user, request.getInvitationCode());
            response.put("family_id", family.getFamilyId());
            response.put("pet_name", family.getPetName());
            response.put("pet_color", family.getPetColor());
            response.put("invitation_code", family.getInvitationCode());
            response.put("family_status", family.getFamilyStatus());
            return ResponseEntity.ok(response);
        } catch (IllegalStateException | IllegalArgumentException e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/leave")
    public ResponseEntity<Map<String, String>> leaveFamily(@RequestBody FamilyDTO.LeaveFamilyRequest request) {
        // 유저 정보 가져오기
        User user = userService.getUserById(request.getUserId());

        Map<String, String> response = new HashMap<>();
        try {
            // 가족 탈퇴
            familyService.leaveFamily(user);
            response.put("message", "성공적으로 가족을 탈퇴했습니다.");
            return ResponseEntity.ok(response);
        } catch (IllegalStateException e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/{family_id}")
    public ResponseEntity<FamilyDTO> getFamily(@PathVariable Long family_id) {
        // 가족 정보 가져오기
        try {
            FamilyDTO family = familyService.getFamily(family_id);
            return ResponseEntity.ok(family);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }



    @GetMapping("/{family_id}/invitation-code")
    public ResponseEntity<Map<String, String>> getInvitationCode(@PathVariable Long family_id) {
        // 가족 초대 코드 가져오기
        try {
            String invitation_code = familyService.getInvitationCode(family_id);

            Map<String, String> response = new HashMap<>();
            response.put("invitation_code", invitation_code);

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

//    -------------------- 애착이 설정 부분 ---------------------

    // 가족별 펫 이름 설정
    @PostMapping("/{familyId}/pet-name")
    public ResponseEntity<FamilyDTO> setPetName(@PathVariable Long familyId, @RequestBody String petName) {
        FamilyDTO familyDTO = familyService.setPetName(familyId, petName);
        return ResponseEntity.status(HttpStatus.CREATED).body(familyDTO);
    }

    // 가족별 펫 이름 수정
    @PatchMapping("/{familyId}/pet-name")
    public ResponseEntity<FamilyDTO> updatePetName(@PathVariable Long familyId, @RequestBody String petName) {
        FamilyDTO familyDTO = familyService.updatePetName(familyId, petName);
        return ResponseEntity.ok(familyDTO);
    }

    // 가족별 펫 색상 설정
    @PostMapping("/{familyId}/pet-color")
    public ResponseEntity<FamilyDTO> setPetColor(@PathVariable Long familyId, @RequestBody PetColor petColor) {
        FamilyDTO familyDTO = familyService.setPetColor(familyId, petColor);
        return ResponseEntity.status(HttpStatus.CREATED).body(familyDTO);
    }

    // 가족별 펫 색상 수정
    @PatchMapping("/{familyId}/pet-color")
    public ResponseEntity<FamilyDTO> updatePetColor(@PathVariable Long familyId, @RequestBody PetColor petColor) {
        FamilyDTO familyDTO = familyService.updatePetColor(familyId, petColor);
        return ResponseEntity.ok(familyDTO);
    }


    // 랜덤 펫을 가족에 할당
    @PatchMapping("/{familyId}/assign-random-pet")
    public ResponseEntity<FamilyDTO> assignRandomPetToFamily(@PathVariable Long familyId) {
        FamilyDTO familyDTO = familyService.assignRandomPetToFamily(familyId);
        return ResponseEntity.ok(familyDTO);
    }


}
