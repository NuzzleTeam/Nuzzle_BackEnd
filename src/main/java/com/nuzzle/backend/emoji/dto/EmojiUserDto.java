package com.nuzzle.backend.emoji.dto;

import lombok.Data;

@Data
public class EmojiUserDto {
    private Long userId;
    private String userName;
    private Long emojiId;
    private String emojiImg;

    public EmojiUserDto(Long userId, String userName, Long emojiId, String emojiImg) {
        this.userId = userId;
        this.userName = userName;
        this.emojiId = emojiId;
        this.emojiImg = emojiImg;
    }
}