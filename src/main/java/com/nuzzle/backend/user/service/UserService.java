package com.nuzzle.backend.user.service;

import com.nuzzle.backend.global.annotation.UserId;
import com.nuzzle.backend.global.dto.ResponseDto;
import com.nuzzle.backend.global.exception.CommonException;
import com.nuzzle.backend.global.exception.ErrorCode;
import com.nuzzle.backend.user.dto.UserResponseDto;
import com.nuzzle.backend.user.repository.UserRepository;
import com.nuzzle.backend.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
    }

    public boolean checkDuplicate(String userName) {
        return userRepository.existsByUserName(userName);
    }

    public User readUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
    }

    @Transactional
    public void updateUser(Long userId, String password){
        User user = getUserById(userId);
        user.updatePassword(password);
    }

}
