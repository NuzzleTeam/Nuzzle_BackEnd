package com.nuzzle.backend.emoji.domain;

import com.nuzzle.backend.picture.domain.mapping.PictureEmoji;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Emoji {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emoji_id")
    private Long emojiId;

    @Column(name = "emoji_img")
    private String emojiImg;

    @OneToMany(mappedBy = "emoji")
    private List<PictureEmoji> pictureEmojis;
}
