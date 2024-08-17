package com.nuzzle.backend.user.service;

import com.nuzzle.backend.global.exception.CommonException;
import com.nuzzle.backend.global.exception.ErrorCode;
import com.nuzzle.backend.user.dto.UserResponseDto;
import com.nuzzle.backend.user.repository.UserRepository;
import com.nuzzle.backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto readUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        return UserResponseDto.fromEntity(user);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
    }

    public boolean checkDuplicate(String userName) {
        return userRepository.existsByUserName(userName);
    }



}
