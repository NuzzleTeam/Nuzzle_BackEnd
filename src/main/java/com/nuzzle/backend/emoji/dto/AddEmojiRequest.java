package com.nuzzle.backend.emoji.dto;

import lombok.Data;

@Data
public class AddEmojiRequest {
    private Long userId;
    private Long emojiId;
}
