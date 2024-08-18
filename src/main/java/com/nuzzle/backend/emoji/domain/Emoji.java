package com.nuzzle.backend.emoji.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Emoji {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "emoji_id")
    private Long emojiId;

    @Column(name = "emoji_img")
    private String emojiImg;

}
