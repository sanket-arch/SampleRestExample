package com.api.book.bootrestbook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.book.bootrestbook.helper.FileUploadHelper;

@RestController
public class FileUploadController {

    @Autowired
    FileUploadHelper fileUploadHelper;

    @PostMapping("/upload-file")
    public ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile file) {

        try {

            if (file.isEmpty()) {
                return ResponseEntity.internalServerError().body("no file selected");
            }

            if (!file.getContentType().equals("image/jpg")) {
                return ResponseEntity.internalServerError().body("only jpg allowed");

            }
            boolean fileUploaded = fileUploadHelper.uploadFile(file);

            if (fileUploaded) {
                return ResponseEntity.ok("File uploaded successfully");
            } else {
                return ResponseEntity.internalServerError().body("something went Wrong");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Something went wrong");
        }

    }
}
