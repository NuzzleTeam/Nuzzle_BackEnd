package com.nuzzle.backend.user.controller;

import com.html.cifarm.dto.global.ResponseDto;
import com.html.cifarm.dto.request.AuthSignUpDto;
import com.html.cifarm.dto.request.OauthLoginDto;
import com.html.cifarm.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Auth", description = "인증관련 API")
@RequestMapping("/api/v1")
public class AuthController {
    private final AuthService authService;


    @GetMapping("/auth/email-duplicate")
    @Operation(summary = "이메일 중복 확인", description = "이메일 중복을 확인합니다.")
    public ResponseDto<?> checkDuplicate(
            @RequestParam(value = "email") String email
    ) {
        return ResponseDto.ok(authService.checkDuplicate(email));
    }

    @PostMapping("/auth/sign-up")
    @Operation(summary = "Default 회원가입", description = "Default 회원가입을 진행합니다.")
    public ResponseDto<?> signUp(
            @RequestBody @Valid AuthSignUpDto authSignUpDto
    ) {
        authService.signUp(authSignUpDto);

        return ResponseDto.ok(null);
    }

    @PostMapping("/auth/login")
    @Operation(summary = "로그인", description = "클라이언트 사이드 인증을 통한 로그인")
    @Schema(name = "login", description = "로그인")
    public ResponseDto<?> login(@RequestBody OauthLoginDto userloginDto) {
        return ResponseDto.ok(authService.login(userloginDto));
    }
}