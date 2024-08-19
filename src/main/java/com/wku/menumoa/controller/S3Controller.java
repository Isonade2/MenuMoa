package com.wku.menumoa.controller;


import com.wku.menumoa.service.S3ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
public class S3Controller {
    private final S3ImageService s3Service;

    @PostMapping("/s3/upload")
    public ResponseEntity<?> s3upload(@RequestPart(value = "image", required = false) MultipartFile image) {
        String img = s3Service.upload(image);
        return ResponseEntity.ok(img);
    }

    @GetMapping("/s3/delete")
    public ResponseEntity<?> s3delete(@RequestParam String addr) {
        log.info("[s3delete Controller]");
        s3Service.deleteImageFromS3(addr);
        return ResponseEntity.ok("Deleted");
    }
}
