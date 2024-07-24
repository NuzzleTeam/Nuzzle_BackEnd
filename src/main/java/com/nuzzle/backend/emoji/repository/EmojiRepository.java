package com.nuzzle.backend.emoji.repository;

import com.nuzzle.backend.emoji.domain.Emoji;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmojiRepository extends JpaRepository<Emoji, Long> {
}
