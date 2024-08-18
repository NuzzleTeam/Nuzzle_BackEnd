package com.nuzzle.backend.emoji.dto;

import lombok.Data;

@Data
public class EmojiDto {
    private Long emojiId;
    private String emojiImg;

    public EmojiDto(Long emojiId, String emojiImg) {
        this.emojiId = emojiId;
        this.emojiImg = emojiImg;
    }
}
