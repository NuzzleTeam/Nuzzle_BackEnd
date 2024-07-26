package com.nuzzle.backend.user.service;

import com.nuzzle.backend.user.domain.User;

public interface UserService {
    User getUserById(Long userId); // 유저 정보를 ID로 가져오는 메서드
}
