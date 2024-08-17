package com.nuzzle.backend.emoji.controller;


import com.nuzzle.backend.emoji.dto.EmojiUserDto;
import com.nuzzle.backend.picture.domain.mapping.PictureEmoji;
import com.nuzzle.backend.picture.service.PictureEmojiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/emoji")
public class EmojiController {


    @Autowired
    private PictureEmojiService pictureEmojiService;

    @GetMapping("/pictures/{pictureId}/emojis")
    public ResponseEntity<List<EmojiUserDto>> getEmojisByPicture(@PathVariable Long pictureId) {
        List<PictureEmoji> pictureEmojis = pictureEmojiService.getEmojisByPictureId(pictureId);

        List<EmojiUserDto> response = pictureEmojis.stream().map(pe -> new EmojiUserDto(
                pe.getUser().getUserId(),
                pe.getUser().getUserName(),
                pe.getEmoji().getEmojiId(),
                pe.getEmoji().getEmojiImg()
        )).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}
