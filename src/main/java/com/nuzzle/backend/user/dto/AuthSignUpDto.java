package com.nuzzle.backend.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nuzzle.backend.global.dto.type.EFamilyRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Schema(name = "AuthSignUpDto", description = "회원가입 요청")
public record AuthSignUpDto(
        @JsonProperty("serial_id") @Schema(description = "시리얼 ID", example = "example@example.com")
        @NotNull(message = "serial_id는 null이 될 수 없습니다.")
        @Size(min = 6, max = 254, message = "시리얼 ID는 6~254자리로 입력해주세요.")
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "유효하지 않은 이메일 형식입니다.")
        String serialId,

        @JsonProperty("password") @Schema(description = "비밀번호", example = "1234567890Aa!")
        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%]).{10,20}$",
                message = "비밀번호는 대문자 1개 이상, 소문자 1개 이상, 숫자 1개 이상, 특수문자(!, @, #, %, $) 1개 이상으로 구성된 10~20자리 비밀번호로 입력해주세요.")
        @NotNull(message = "password는 null이 될 수 없습니다.")
        String password,

        @JsonProperty("user_name") @Schema(description = "사용자 이름", example = "너즐이")
        @NotNull(message = "user_name은 null이 될 수 없습니다.")
        String userName,

        @JsonProperty("gender") @Schema(description = "성별", example = "MALE, FEMALE")
        @NotNull(message = "gender는 null이 될 수 없습니다.")
        String gender,

        @JsonProperty("birth_date") @Schema(description = "생년월일", example = "1990-01-01")
        @NotNull(message = "birth_date는 null이 될 수 없습니다.")
        LocalDate birthDate,

        @JsonProperty("role") @Schema(description = "가족 역할", example = "PARENT, CHILDREN, ETC")
        @NotNull(message = "role은 null이 될 수 없습니다.")
        EFamilyRole familyrole
) {
}