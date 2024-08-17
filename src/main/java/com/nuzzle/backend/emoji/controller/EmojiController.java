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



    @GetMapping("/all")
    public ResponseEntity<List<EmojiDto>> getAllEmojis() {
        List<EmojiDto> emojis = emojiService.findAllEmojis();
        return ResponseEntity.ok(emojis);
    }

}
