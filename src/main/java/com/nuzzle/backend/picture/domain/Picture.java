package com.nuzzle.backend.picture.domain;

import com.nuzzle.backend.picture.domain.mapping.PictureEmoji;
import com.nuzzle.backend.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "picture_id")
    private Long id;

    private String fileName;
    private String fileType;
    private long size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "picture")
    private List<PictureEmoji> pictureEmojis;

    // 이 부분 사진에 대해서 url로 aws 에 올린다 생각해서 아래처럼 url로 설계했는데 위 fileName과 fileType, size가 저랑 다르게 구현한 부분인 것 같습니다. 난영님과 얘기 해봐야 할 것 같습니다.
    @Column(name = "picture_url")
    private String pictureURL;

    @Column(name = "upload_date")
    private LocalDateTime uploadDate;
    //
    public Picture(String fileName, String fileType, long size) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.size = size;
    }
}
