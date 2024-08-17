package com.nuzzle.backend.emoji.controller;

import com.nuzzle.backend.emoji.dto.AddEmojiRequest;

import com.nuzzle.backend.emoji.dto.PictureEmojiResponseDTO;
import com.nuzzle.backend.picture.domain.mapping.PictureEmoji;
import com.nuzzle.backend.picture.service.PictureEmojiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/emoji")
public class EmojiController {



    @Autowired
    private PictureEmojiService pictureEmojiService;



    @PostMapping("/pictures/{pictureId}/emojis")
    public ResponseEntity<PictureEmojiResponseDTO> addEmojiToPicture(@PathVariable Long pictureId,
                                                                     @RequestBody AddEmojiRequest request) {
        PictureEmoji pictureEmoji = pictureEmojiService.addEmojiToPicture(pictureId, request.getUserId(), request.getEmojiId());

        PictureEmojiResponseDTO responseDTO = new PictureEmojiResponseDTO(
                pictureEmoji.getPictureEmojiId(),
                pictureEmoji.getEmoji().getEmojiId(),
                pictureEmoji.getEmoji().getEmojiImg(),
                pictureEmoji.getPicture().getPictureId(),
                pictureEmoji.getPicture().getPictureURL(),
                pictureEmoji.getUser().getUserId(),
                pictureEmoji.getUser().getUserName()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

}
