package com.nuzzle.backend.emoji.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Emoji {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emojiId;

    private String emojiImg;
}
