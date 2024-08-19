package com.nuzzle.backend.picture.domain.mapping;

import com.nuzzle.backend.emoji.domain.Emoji;
import com.nuzzle.backend.picture.domain.Picture;
import com.nuzzle.backend.user.domain.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PictureEmoji {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_emoji_id")
    private Long pictureEmojiId;

    @ManyToOne
    @JoinColumn(name = "emoji_id")
    private Emoji emoji;

    @ManyToOne
    @JoinColumn(name = "picture_id")
    private Picture picture;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  // 이모티콘을 등록한 유저
}
