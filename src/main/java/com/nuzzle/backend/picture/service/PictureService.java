package com.nuzzle.backend.picture.service;

import com.nuzzle.backend.picture.domain.Picture;

public interface PictureService {
    Picture findPictureById(Long pictureId);
}
