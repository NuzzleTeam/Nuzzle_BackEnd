package com.nuzzle.backend.emoji.dto;

import lombok.Data;

@Data
public class PictureEmojiResponseDTO {
    private Long pictureEmojiId;
    private Long emojiId;
    private String emojiImg;
    private Long pictureId;
    private String pictureURL;
    private Long userId;
    private String userName;

    public PictureEmojiResponseDTO(Long pictureEmojiId, Long emojiId, String emojiImg, Long pictureId, String pictureURL, Long userId, String userName) {
        this.pictureEmojiId = pictureEmojiId;
        this.emojiId = emojiId;
        this.emojiImg = emojiImg;
        this.pictureId = pictureId;
        this.pictureURL = pictureURL;
        this.userId = userId;
        this.userName = userName;
    }
}
