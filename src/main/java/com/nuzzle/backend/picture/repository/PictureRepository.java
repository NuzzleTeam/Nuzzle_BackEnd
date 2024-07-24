package com.nuzzle.backend.picture.repository;

import com.nuzzle.backend.picture.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
