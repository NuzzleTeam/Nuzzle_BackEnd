package com.nuzzle.backend.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nuzzle.backend.global.dto.type.EProvider;
import com.nuzzle.backend.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserResponseDto(
        @Schema(description = "유저 ID", example = "1")
        @NotNull(message = "유저 ID가 없습니다.")
        Long userId,

        @Schema(description = "사용자 이름", example = "너즐이")
        @NotNull(message = "사용자 이름이 없습니다.")
        String userName,

        @Schema(description = "로그인 제공자", example = "KAKAO, GOOGLE, APPLE, DEFAULT")
        @NotNull(message = "로그인 제공자가 없습니다.")
        EProvider provider

) {
    public static UserResponseDto fromEntity(User user) {
        return UserResponseDto.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .provider(user.getProvider())
                .build();
    }
}