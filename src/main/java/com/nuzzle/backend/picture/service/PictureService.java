package com.nuzzle.backend.picture.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.nuzzle.backend.picture.domain.Picture;
import com.nuzzle.backend.picture.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class PictureService {

    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    private PictureRepository photoRepository;

    private String bucketName = "nuzzle-bucket"; // 버킷 이름 설정

    public String store(MultipartFile file) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        // S3에 파일 업로드
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), metadata));

        // 데이터베이스에 파일 메타데이터 저장
        Picture photo = new Picture(fileName, file.getContentType(), file.getSize());
        photoRepository.save(photo);

        // 저장된 파일 이름을 반환
        return fileName;
    }

    public Resource loadAsResource(String filename) {
        try {
            System.out.println("Loading file from S3: " + filename);
            S3Object s3Object = s3Client.getObject(bucketName, filename);
            return new InputStreamResource(s3Object.getObjectContent());
        } catch (AmazonS3Exception e) {
            System.err.println("File not found in S3: " + filename);
            throw new RuntimeException("File not found in S3", e);
        }
    }

}