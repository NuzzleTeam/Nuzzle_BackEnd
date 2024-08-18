package com.nuzzle.backend.user.service;

import com.nuzzle.backend.global.dto.JwtTokenDto;
import com.nuzzle.backend.global.dto.type.ERole;
import com.nuzzle.backend.global.exception.CommonException;
import com.nuzzle.backend.global.exception.ErrorCode;
import com.nuzzle.backend.global.utility.JwtUtil;
import com.nuzzle.backend.user.domain.User;
import com.nuzzle.backend.user.dto.AuthSignUpDto;
import com.nuzzle.backend.user.dto.OauthLoginDto;
import com.nuzzle.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void signUp(AuthSignUpDto authSignUpDto) {
        userRepository.save(
                User.signUp(authSignUpDto, bCryptPasswordEncoder.encode(authSignUpDto.password()))
        );
    }

    @Transactional
    public JwtTokenDto reissue(Long userId, String refreshToken) {
        User user = userRepository.findByUserIdAndRefreshTokenAndIsLogin(userId, refreshToken, true)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_LOGIN_USER));

        JwtTokenDto jwtTokenDto = jwtUtil.generateTokens(user.getUserId(), user.getRole());
        user.updateRefreshToken(jwtTokenDto.refreshToken());

        return jwtTokenDto;
    }

    public Object checkDuplicate(String email) {
        return userRepository.existsBySerialId(email);
    }

    @Transactional
    public JwtTokenDto login(OauthLoginDto userLoginDto) {
        User user;
        boolean isNewUser = false;

        Optional<User> existingUser = userRepository.findBySerialId(userLoginDto.serialId());

        if (existingUser.isPresent()) {
            user = existingUser.get();
        } else {
            user = userRepository.save(User.signUp(userLoginDto.serialId(), userLoginDto.provider()));
            isNewUser = true;
        }

        JwtTokenDto jwtTokenDto = jwtUtil.generateTokens(user.getUserId(), ERole.USER);

        if (isNewUser || !jwtTokenDto.refreshToken().equals(user.getRefreshToken())) {
            userRepository.updateRefreshTokenAndLoginStatus(user.getUserId(), jwtTokenDto.refreshToken(), true);
        }

        return jwtTokenDto;
    }
}