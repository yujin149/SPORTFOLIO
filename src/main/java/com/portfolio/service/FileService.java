package com.portfolio.service;


import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Log
public class FileService {
    @Value("${projectImgLocation}") // application.properties에서 경로를 주입받음
    private String uploadPath;

    // 파일 업로드
    public String uploadFile(String uploadPath, String originalFileName, byte[] fileData) throws Exception {
        UUID uuid = UUID.randomUUID(); // UUID 생성
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String savedFileName = uuid.toString() + extension; // 저장될 파일 이름 생성
        Path targetPath = Paths.get(uploadPath, savedFileName);

        // 업로드 경로에 디렉토리가 없다면 생성
        if (!Files.exists(Paths.get(uploadPath))) {
            Files.createDirectories(Paths.get(uploadPath)); // 디렉토리 생성
        }

        // 파일을 업로드 경로에 저장
        try (FileOutputStream fos = new FileOutputStream(targetPath.toFile())) {
            fos.write(fileData); // 파일 데이터 쓰기
        }

        return savedFileName; // 저장된 파일 이름 반환
    }

    // 파일 삭제
    public void deleteFile(String fileName) {
        try {
            Path filePath = Paths.get(uploadPath).resolve(fileName);
            Files.deleteIfExists(filePath); // 파일이 존재하면 삭제
        } catch (IOException e) {
            throw new RuntimeException("파일 삭제 중 오류가 발생했습니다: " + fileName, e);
        }
    }

}
