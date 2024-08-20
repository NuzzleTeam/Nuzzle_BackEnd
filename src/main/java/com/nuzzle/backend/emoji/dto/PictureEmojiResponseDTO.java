package com.nuzzle.backend.emoji.dto;

import lombok.Data;

@Data
public class PictureEmojiResponseDTO {
    private Long pictureEmojiId;
    private Long emojiId;
    private String emojiImg;
    private Long pictureId;
    private String fileName;  // 기존의 pictureURL 대신 fileName으로 변경
    private Long userId;
    private String userName;

    public PictureEmojiResponseDTO(Long pictureEmojiId, Long emojiId, String emojiImg, Long pictureId, String fileName, Long userId, String userName) {
        this.pictureEmojiId = pictureEmojiId;
        this.emojiId = emojiId;
        this.emojiImg = emojiImg;
        this.pictureId = pictureId;
        this.fileName = fileName;  // 기존의 pictureURL 대신 fileName으로 변경
        this.userId = userId;
        this.userName = userName;
    }
}
