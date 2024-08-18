package com.nuzzle.backend.picture.domain;

<<<<<<< HEAD
import com.nuzzle.backend.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fileName;
    private String fileType;
    private long size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Picture(String fileName, String fileType, long size) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.size = size;
    }
=======
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
>>>>>>> 0fea6e0a7e3363f534cc21777b34438b51ee72c1
}
