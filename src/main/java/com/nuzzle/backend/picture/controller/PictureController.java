package com.nuzzle.backend.picture.controller;

import com.nuzzle.backend.picture.service.PictureService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/v1")
public class PictureController {

    private final PictureService photoService;

    public PictureController(PictureService photoService) {
        this.photoService = photoService;
    }

    @PostMapping("/picture")
    public ResponseEntity<String> uploadPhoto(@RequestPart("file") MultipartFile file) {
        try {
            // S3에 저장할 때 사용한 파일 이름을 반환받음
            String storedFileName = photoService.store(file);

            // 반환된 파일 이름으로 URI 생성
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .scheme("https")  // HTTPS로 강제 설정
                    .path("/pictures/")
                    .path(storedFileName)  // 저장된 파일 이름으로 URI 생성
                    .toUriString();

            return ResponseEntity.ok("File successfully uploaded: " + fileDownloadUri);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to upload file: " + e.getMessage());
        }
    }


    @GetMapping("/picture/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        try {
            // 파일 이름을 UTF-8로 디코딩
            String decodedFilename = URLDecoder.decode(filename, StandardCharsets.UTF_8.name());

            // S3에서 파일을 로드
            Resource file = photoService.loadAsResource(decodedFilename);

            // 파일이 존재하지 않을 경우 예외 발생
            if (file == null || !file.exists()) {
                throw new FileNotFoundException("File not found: " + decodedFilename);
            }

            // 파일 이름을 UTF-8로 인코딩하여 안전한 파일 이름 생성
            String safeFilename = URLEncoder.encode(file.getFilename(), StandardCharsets.UTF_8.name()).replace("+", "%20");

            // 파일 다운로드 제공
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + safeFilename + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(file);
        } catch (FileNotFoundException e) {
            // 파일을 찾을 수 없을 때 404 오류를 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (UnsupportedEncodingException e) {
            // 인코딩 문제 발생 시 400 오류 반환
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            // 기타 예외 처리, 500 내부 서버 오류 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // GetMapping 어노테이션을 사용하여 HTTP GET 요청을 '/hello' 경로로 매핑
    @GetMapping("/hello")
    public String sayHello() {
        // 요청을 받으면 "Hello, World!" 문자열을 반환
        return "Hello, World!";
    }


}
