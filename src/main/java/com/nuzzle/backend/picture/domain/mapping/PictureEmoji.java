package com.nuzzle.backend.picture.domain.mapping;

import com.nuzzle.backend.emoji.domain.Emoji;
import com.nuzzle.backend.picture.domain.Picture;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PictureEmoji {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pictureId;

    @ManyToOne
    @JoinColumn(name = "emojiId")
    private Emoji emoji;

    @ManyToOne
    @JoinColumn(name = "emojiId")
    private Picture picture;
}
