package com.nuzzle.backend.emoji.controller;


import com.nuzzle.backend.emoji.dto.EmojiDto;
import com.nuzzle.backend.emoji.service.EmojiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emoji")
public class EmojiController {

    @Autowired
    private EmojiService emojiService;



    @GetMapping("/users/{userId}/recent-emojis")
    public ResponseEntity<List<EmojiDto>> getRecentEmojisByUser(@PathVariable Long userId) {
        List<EmojiDto> recentEmojis = emojiService.findRecentEmojisByUser(userId, 5);
        return ResponseEntity.ok(recentEmojis);
    }

}
