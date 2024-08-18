package com.nuzzle.backend.security.info;


import com.nuzzle.backend.global.dto.type.ERole;

public record JwtUserInfo(Long userId, ERole role) {
}