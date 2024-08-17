package com.nuzzle.backend.picture.service.impl;

import com.nuzzle.backend.picture.domain.Picture;
import com.nuzzle.backend.picture.repository.PictureRepository;
import com.nuzzle.backend.picture.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public Picture findPictureById(Long pictureId) {
        return pictureRepository.findById(pictureId)
                .orElseThrow(() -> new RuntimeException("Picture not found"));
    }
}
