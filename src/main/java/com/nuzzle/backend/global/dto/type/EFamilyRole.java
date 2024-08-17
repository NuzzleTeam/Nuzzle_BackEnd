package com.nuzzle.backend.global.dto.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EFamilyRole {
    PARENT("PARENT"),
    CHILDREN("CHILDREN"),
    ETC("ETC");

    private final String value;

    @Override
    public String toString() {
        return value;
    }

}
