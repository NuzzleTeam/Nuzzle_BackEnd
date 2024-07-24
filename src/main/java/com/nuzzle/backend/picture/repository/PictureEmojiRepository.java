package com.nuzzle.backend.picture.repository;

import com.nuzzle.backend.picture.domain.mapping.PictureEmoji;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureEmojiRepository extends JpaRepository<PictureEmoji, Long> {
}
