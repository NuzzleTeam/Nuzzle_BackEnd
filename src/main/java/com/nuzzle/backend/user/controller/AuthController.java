package com.nuzzle.backend.user.controller;

import com.nuzzle.backend.global.annotation.UserId;
import com.nuzzle.backend.global.constants.Constants;
import com.nuzzle.backend.global.dto.JwtTokenDto;
import com.nuzzle.backend.global.dto.ResponseDto;
import com.nuzzle.backend.global.exception.CommonException;
import com.nuzzle.backend.global.exception.ErrorCode;
import com.nuzzle.backend.global.utility.HeaderUtil;
import com.nuzzle.backend.user.service.AuthService;
import com.nuzzle.backend.user.dto.AuthSignUpDto;
import com.nuzzle.backend.user.dto.OauthLoginDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

    @PostMapping("/oauth/login")
    @Operation(summary = "소셜로그인", description = "클라이언트 사이드 인증을 통한 소셜 로그인")
    @Schema(name = "login", description = "로그인")
    public ResponseDto<?> login(@RequestBody OauthLoginDto userloginDto) {
        return ResponseDto.ok(authService.login(userloginDto));
    }

    @PostMapping("/auth/reissue")
    @Operation(summary = "Access 토큰 재발급", description = "Access 토큰을 재발급합니다.")
    public ResponseDto<?> reissue(
            HttpServletRequest request,
            HttpServletResponse response,
            @UserId Long userId) {
        String refreshToken = HeaderUtil.refineHeader(request, Constants.AUTHORIZATION_HEADER, Constants.BEARER_PREFIX)
                .orElseThrow(() -> new CommonException(ErrorCode.MISSING_REQUEST_HEADER));

        JwtTokenDto jwtTokenDto = authService.reissue(userId, refreshToken);

        return ResponseDto.ok(jwtTokenDto);
    }

}