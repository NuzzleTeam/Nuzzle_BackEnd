package com.nuzzle.backend.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(name = "UserUpdateDto", description = "유저 정보 수정 요청")
public record UserUpdateDto(
        @Schema(description = "닉네임", example = "개똥이")
        @Size(min = 2, max = 10, message = "닉네임은 2~10자리로 입력해주세요.")
        @NotNull(message = "닉네임은 null이 될 수 없습니다.")
        String nickname
) {
}