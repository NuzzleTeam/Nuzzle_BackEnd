package com.nuzzle.backend.picture.repository;

import com.nuzzle.backend.picture.domain.Picture;
import com.nuzzle.backend.picture.domain.mapping.PictureEmoji;
import com.nuzzle.backend.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PictureEmojiRepository extends JpaRepository<PictureEmoji, Long> {

        List<PictureEmoji> findTop5ByUserOrderByPictureEmojiIdDesc(User user);  // 특정
}
