package com.nuzzle.backend.security.handler.login;

import com.nuzzle.backend.global.dto.JwtTokenDto;
import com.nuzzle.backend.global.utility.CookieUtil;
import com.nuzzle.backend.global.utility.JwtUtil;
import com.nuzzle.backend.security.info.UserPrincipal;
import com.nuzzle.backend.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONValue;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DefaultSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        JwtTokenDto tokenDto = jwtUtil.generateTokens(userPrincipal.getUserId(), userPrincipal.getRole());
        userRepository.updateRefreshTokenAndLoginStatus(userPrincipal.getUserId(), tokenDto.refreshToken(), true);

        setSuccessAppResponse(response, tokenDto);
    }

    private void setSuccessAppResponse(HttpServletResponse response, JwtTokenDto tokenDto) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.OK.value());

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", Map.of(
                        "access_token", tokenDto.accessToken(),
                        "refresh_token", tokenDto.refreshToken()
                )
        );
        result.put("error", null);

        response.getWriter().write(JSONValue.toJSONString(result));
    }

    private void setSuccessWebResponse(HttpServletResponse response, JwtTokenDto tokenDto) throws IOException {
        CookieUtil.addCookie(response, "access_token", tokenDto.accessToken());
        CookieUtil.addSecureCookie(response, "refresh_token", tokenDto.refreshToken(), jwtUtil.getRefreshExpiration());
    }
}