package com.nuzzle.backend.emoji.controller;


import com.nuzzle.backend.emoji.dto.AddEmojiRequest;
import com.nuzzle.backend.emoji.dto.EmojiDto;
import com.nuzzle.backend.emoji.dto.EmojiUserDto;
import com.nuzzle.backend.emoji.dto.PictureEmojiResponseDTO;
import com.nuzzle.backend.emoji.service.EmojiService;
import com.nuzzle.backend.picture.domain.mapping.PictureEmoji;
import com.nuzzle.backend.picture.service.PictureEmojiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/emoji")
public class EmojiController {

    @Autowired
    private EmojiService emojiService;

    @Autowired
    private PictureEmojiService pictureEmojiService;

    @GetMapping("/all")
    public ResponseEntity<List<EmojiDto>> getAllEmojis() {
        List<EmojiDto> emojis = emojiService.findAllEmojis();
        return ResponseEntity.ok(emojis);
    }

    @GetMapping("/users/{userId}/recent-emojis")
    public ResponseEntity<List<EmojiDto>> getRecentEmojisByUser(@PathVariable Long userId) {
        List<EmojiDto> recentEmojis = emojiService.findRecentEmojisByUser(userId, 5);
        return ResponseEntity.ok(recentEmojis);
    }

    @PostMapping("/pictures/{pictureId}/emojis")
    public ResponseEntity<PictureEmojiResponseDTO> addEmojiToPicture(@PathVariable Long pictureId,
                                                                     @RequestBody AddEmojiRequest request) {
        PictureEmoji pictureEmoji = pictureEmojiService.addEmojiToPicture(pictureId, request.getUserId(), request.getEmojiId());

        PictureEmojiResponseDTO responseDTO = new PictureEmojiResponseDTO(
                pictureEmoji.getPictureEmojiId(),
                pictureEmoji.getEmoji().getEmojiId(),
                pictureEmoji.getEmoji().getEmojiImg(),
                pictureEmoji.getPicture().getId(),  // 변경된 필드 사용
                pictureEmoji.getPicture().getFileName(),  // pictureURL 대신 fileName 사용
                pictureEmoji.getUser().getUserId(),
                pictureEmoji.getUser().getUserName()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

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
