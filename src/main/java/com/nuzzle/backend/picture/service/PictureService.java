package com.nuzzle.backend.picture.service;

import com.nuzzle.backend.picture.domain.Picture;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PictureService {

    String store(MultipartFile file) throws IOException;

    Resource loadAsResource(String filename);

    Picture findPictureById(Long pictureId);


}
