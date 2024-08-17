package com.nuzzle.backend.emoji.service.impl;

import com.nuzzle.backend.emoji.domain.Emoji;
import com.nuzzle.backend.emoji.dto.EmojiDto;
import com.nuzzle.backend.emoji.repository.EmojiRepository;
import com.nuzzle.backend.emoji.service.EmojiService;
import com.nuzzle.backend.picture.domain.mapping.PictureEmoji;
import com.nuzzle.backend.picture.repository.PictureEmojiRepository;
import com.nuzzle.backend.user.domain.User;
import com.nuzzle.backend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmojiServiceImpl implements EmojiService {

    @Autowired
    private EmojiRepository emojiRepository;

    @Autowired
    private PictureEmojiRepository pictureEmojiRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<EmojiDto> findAllEmojis() {
        return emojiRepository.findAll().stream()
                .map(emoji -> new EmojiDto(emoji.getEmojiId(), emoji.getEmojiImg()))
                .collect(Collectors.toList());
    }

    @Override
    public List<EmojiDto> findRecentEmojisByUser(Long userId, int count) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<PictureEmoji> recentPictureEmojis = pictureEmojiRepository.findTop5ByUserOrderByPictureEmojiIdDesc(user);

        // 중복되지 않은 최근 사용 이모티콘 목록 추출
        Set<Long> uniqueEmojiIds = new HashSet<>();
        List<EmojiDto> recentEmojis = recentPictureEmojis.stream()
                .filter(pe -> uniqueEmojiIds.add(pe.getEmoji().getEmojiId())) // 중복 제거
                .map(pe -> new EmojiDto(pe.getEmoji().getEmojiId(), pe.getEmoji().getEmojiImg()))
                .collect(Collectors.toList());

        // 만약 중복 제거 후 5개 미만일 경우 기본값 이모티콘 추가
        if (recentEmojis.size() < count) {
            List<Emoji> defaultEmojis = emojiRepository.findAllById(Arrays.asList(1L, 2L, 3L, 4L, 5L));
            Set<Long> existingEmojiIds = recentEmojis.stream().map(EmojiDto::getEmojiId).collect(Collectors.toSet());

            for (Emoji defaultEmoji : defaultEmojis) {
                if (recentEmojis.size() >= count) {
                    break;
                }
                if (!existingEmojiIds.contains(defaultEmoji.getEmojiId())) {
                    recentEmojis.add(new EmojiDto(defaultEmoji.getEmojiId(), defaultEmoji.getEmojiImg()));
                }
            }
        }

        return recentEmojis;
    }

    @Override
    public Emoji saveEmoji(Emoji emoji) {
        return emojiRepository.save(emoji);
    }
}
