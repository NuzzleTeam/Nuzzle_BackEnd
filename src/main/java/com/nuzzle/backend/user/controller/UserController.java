package com.nuzzle.backend.user.controller;

import com.nuzzle.backend.global.annotation.UserId;
import com.nuzzle.backend.global.dto.ResponseDto;
import com.nuzzle.backend.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Tag(name = "User", description = "유저 관련 API")
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("")
    @Operation(summary = "본인 정보 조회", description = "본인 정보를 조회합니다.")
    public ResponseDto<?> readUsers(
            @UserId Long userId
    ) {
        return ResponseDto.ok(userService.readUser(userId));
    }

    @PatchMapping(value = "/password", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "비밀번호 변경", description = "유저 비밀번호를 수정합니다.")
    public ResponseDto<?> updateUser(
            @UserId Long userId,
            @RequestParam(required = false) String password) {
        userService.updateUser(userId, password);
        return ResponseDto.ok(null);
    }
}
