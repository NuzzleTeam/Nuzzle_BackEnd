package com.nuzzle.backend.emoji.domain;

<<<<<<< HEAD
=======
import com.nuzzle.backend.picture.domain.mapping.PictureEmoji;
>>>>>>> c9f35ada6c60f5934cc32d4d0762fd6c01719896
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Emoji {
    @Id
<<<<<<< HEAD
    @GeneratedValue(strategy = GenerationType.AUTO)
=======
    @GeneratedValue(strategy = GenerationType.IDENTITY)
>>>>>>> c9f35ada6c60f5934cc32d4d0762fd6c01719896
    @Column(name = "emoji_id")
    private Long emojiId;

    @Column(name = "emoji_img")
    private String emojiImg;

<<<<<<< HEAD
=======
    @OneToMany(mappedBy = "emoji")
    private List<PictureEmoji> pictureEmojis;
>>>>>>> c9f35ada6c60f5934cc32d4d0762fd6c01719896
}
