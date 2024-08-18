package com.nuzzle.backend.global.dto.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EProvider {
    DEFAULT("DEFAULT"),
    KAKAO("KAKAO");

    private final String name;

    @Override
    public String toString() {
        return name;
    }
}