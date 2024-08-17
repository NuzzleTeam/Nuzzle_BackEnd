package com.nuzzle.backend.picture.service;

import com.nuzzle.backend.picture.domain.mapping.PictureEmoji;

import java.util.List;

public interface PictureEmojiService {
    PictureEmoji addEmojiToPicture(Long pictureId, Long userId, Long emojiId);
    List<PictureEmoji> getEmojisByPictureId(Long pictureId);
}
