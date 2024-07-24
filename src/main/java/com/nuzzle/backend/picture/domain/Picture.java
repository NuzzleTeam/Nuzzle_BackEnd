package com.nuzzle.backend.picture.domain;

import com.nuzzle.backend.picture.domain.mapping.PictureEmoji;
import com.nuzzle.backend.user.domain.User;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pictureId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "picture")
    private List<PictureEmoji> pictureEmojis;

    private String pictureURL;
    private LocalDateTime uploadDate;
}
