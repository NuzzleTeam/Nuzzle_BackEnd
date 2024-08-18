package com.nuzzle.backend.family.controller;

import com.nuzzle.backend.family.domain.Family;
<<<<<<< HEAD
import com.nuzzle.backend.family.dto.FamilyDTO;
=======
>>>>>>> 0fea6e0a7e3363f534cc21777b34438b51ee72c1
import com.nuzzle.backend.family.service.FamilyService;
import com.nuzzle.backend.user.domain.User;
import com.nuzzle.backend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
=======
import org.springframework.web.bind.annotation.*;
>>>>>>> 0fea6e0a7e3363f534cc21777b34438b51ee72c1

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/family")
public class FamilyController {
    @Autowired
    private FamilyService family_service;

    @Autowired
    private UserService user_service;

    @PostMapping("/create")
<<<<<<< HEAD
    public ResponseEntity<Map<String, Object>> createFamily(@RequestBody FamilyDTO.CreateFamilyRequest request) {
        // 유저 정보 가져오기
        User user = user_service.getUserById(request.getUserId());

        // 응답 데이터 생성
        Map<String, Object> response = new HashMap<>();
        try {
            // 가족 생성
            FamilyDTO family = family_service.createFamily(user);
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
=======
    public Map<String, Object> createFamily(@RequestParam Long user_id) {
        // 유저 정보 가져오기
        User user = user_service.getUserById(user_id);
        // 가족 생성
        Family family = family_service.createFamily(user);

        // 응답 데이터 생성
        Map<String, Object> response = new HashMap<>();
        response.put("family_id", family.getFamilyId());
        response.put("pet_name", family.getPetName()); // pet_name은 null일 수 있음
        response.put("pet_color", family.getPetColor()); // pet_color는 null일 수 있음
        response.put("invitation_code", family.getInvitationCode());
        response.put("family_status", family.getFamilyStatus());

        return response;
>>>>>>> 0fea6e0a7e3363f534cc21777b34438b51ee72c1
    }

    @PostMapping("/join")
    public Map<String, Object> joinFamily(@RequestParam Long user_id, @RequestParam String invitation_code) {
        // 유저 정보 가져오기
        User user = user_service.getUserById(user_id);
        // 초대 코드로 가족에 합류
        Family family = family_service.joinFamily(user, invitation_code);

        // 응답 데이터 생성
        Map<String, Object> response = new HashMap<>();
        response.put("family_id", family.getFamilyId());
        response.put("pet_name", family.getPetName()); // pet_name은 null일 수 있음
        response.put("pet_color", family.getPetColor()); // pet_color는 null일 수 있음
        response.put("invitation_code", family.getInvitationCode());
        response.put("family_status", family.getFamilyStatus());

        return response;
    }

    @PostMapping("/leave")
<<<<<<< HEAD
    public ResponseEntity<Map<String, String>> leaveFamily(@RequestBody FamilyDTO.LeaveFamilyRequest request) {
        // 유저 정보 가져오기
        User user = user_service.getUserById(request.getUserId());

        Map<String, String> response = new HashMap<>();
        try {
            // 가족 탈퇴
            family_service.leaveFamily(user);
            response.put("message", "성공적으로 가족을 탈퇴했습니다.");
            return ResponseEntity.ok(response);
        } catch (IllegalStateException e) {
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
=======
    public Map<String, String> leaveFamily(@RequestParam Long user_id) {
        // 유저 정보 가져오기
        User user = user_service.getUserById(user_id);
        // 가족 탈퇴
        family_service.leaveFamily(user);

        // 응답 메시지 생성
        Map<String, String> response = new HashMap<>();
        response.put("message", "Successfully left the family.");
        return response;
>>>>>>> 0fea6e0a7e3363f534cc21777b34438b51ee72c1
    }

    @GetMapping("/{family_id}")
    public Family getFamily(@PathVariable Long family_id) {
        // 가족 정보 가져오기
        return family_service.getFamily(family_id);
    }

    @GetMapping("/{family_id}/members")
    public Map<String, Object> getFamilyMembers(@PathVariable Long family_id) {
        // 가족 구성원 리스트 가져오기
        List<User> members = family_service.getFamilyMembers(family_id);

        // 응답 데이터 생성
        Map<String, Object> response = new HashMap<>();
        response.put("family_id", family_id);
        response.put("members", members);

        return response;
    }

    @GetMapping("/{family_id}/invitation-code")
<<<<<<< HEAD
    public ResponseEntity<Map<String, String>> getInvitationCode(@PathVariable Long family_id) {
        // 가족 초대 코드 가져오기
        try {
            String invitation_code = family_service.getInvitationCode(family_id);

            Map<String, String> response = new HashMap<>();
            response.put("invitation_code", invitation_code);

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
=======
    public Map<String, String> getInvitationCode(@PathVariable Long family_id) {
        // 가족 초대 코드 가져오기
        String invitation_code = family_service.getInvitationCode(family_id);

        // 응답 데이터 생성
        Map<String, String> response = new HashMap<>();
        response.put("invitation_code", invitation_code);

        return response;
>>>>>>> 0fea6e0a7e3363f534cc21777b34438b51ee72c1
    }
}
