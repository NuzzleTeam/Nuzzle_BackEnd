package com.nuzzle.backend.security.handler.login;

import com.nuzzle.backend.global.dto.JwtTokenDto;
import com.nuzzle.backend.global.utility.JwtUtil;
import com.nuzzle.backend.security.info.AuthenticationResponse;
import com.nuzzle.backend.security.info.UserPrincipal;
import com.nuzzle.backend.user.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class Oauth2SuccessHandler implements AuthenticationSuccessHandler {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        JwtTokenDto jwtTokenDto = jwtUtil.generateTokens(userPrincipal.getUserId(), userPrincipal.getRole());

        userRepository.updateRefreshTokenAndLoginStatus(userPrincipal.getUserId(), jwtTokenDto.refreshToken(), true);

        AuthenticationResponse.makeLoginSuccessResponse(response, jwtTokenDto, jwtUtil.getRefreshExpiration());
    }
}