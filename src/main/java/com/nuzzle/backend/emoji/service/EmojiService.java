package com.nuzzle.backend.emoji.service;

import com.nuzzle.backend.emoji.domain.Emoji;
import com.nuzzle.backend.emoji.dto.EmojiDto;

import java.util.List;
public interface EmojiService {
    List<EmojiDto> findRecentEmojisByUser(Long userId, int count);

}