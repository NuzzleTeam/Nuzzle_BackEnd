package com.nuzzle.backend.user.service.impl;

import com.nuzzle.backend.user.domain.User;
import com.nuzzle.backend.user.repository.UserRepository;
import com.nuzzle.backend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Long userId) {
        // 유저 정보를 ID로 가져오기
        return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
