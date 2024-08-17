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
    @Column(name = "picture_id")
    private Long pictureId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "picture")
    private List<PictureEmoji> pictureEmojis;

    @Column(name = "picture_url")
    private String pictureURL;

    @Column(name = "upload_date")
    private LocalDateTime uploadDate;


}
