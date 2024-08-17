package com.nuzzle.backend.picture.service.impl;

import com.nuzzle.backend.emoji.domain.Emoji;
import com.nuzzle.backend.emoji.repository.EmojiRepository;
import com.nuzzle.backend.picture.domain.Picture;
import com.nuzzle.backend.picture.domain.mapping.PictureEmoji;
import com.nuzzle.backend.picture.repository.PictureEmojiRepository;
import com.nuzzle.backend.picture.repository.PictureRepository;
import com.nuzzle.backend.picture.service.PictureEmojiService;
import com.nuzzle.backend.user.domain.User;
import com.nuzzle.backend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PictureEmojiServiceImpl implements PictureEmojiService {

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private PictureEmojiRepository pictureEmojiRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmojiRepository emojiRepository;

    @Override
    @Transactional
    public PictureEmoji addEmojiToPicture(Long pictureId, Long userId, Long emojiId) {
        Picture picture = pictureRepository.findById(pictureId)
                .orElseThrow(() -> new IllegalArgumentException("Picture not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Emoji emoji = emojiRepository.findById(emojiId)
                .orElseThrow(() -> new IllegalArgumentException("Emoji not found"));

        PictureEmoji pictureEmoji = new PictureEmoji();
        pictureEmoji.setPicture(picture);
        pictureEmoji.setUser(user);  // 이모티콘을 등록한 유저 설정
        pictureEmoji.setEmoji(emoji);

        return pictureEmojiRepository.save(pictureEmoji);
    }


}