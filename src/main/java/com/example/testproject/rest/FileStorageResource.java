package com.example.testproject.rest;

import com.example.testproject.domain.FileStorage;
import com.example.testproject.domain.Student;
import com.example.testproject.service.FileStorageService;
import com.example.testproject.service.StudentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api")
public class FileStorageResource {

    private final FileStorageService fileStorageService;
    private final StudentService studentService;

    @Value("${upload.folder}")
    private String uploadFolder;

    public FileStorageResource(FileStorageService fileStorageService, StudentService studentService) {
        this.fileStorageService = fileStorageService;
        this.studentService = studentService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("student_id") Long studentId){
        Student student = studentService.findById(studentId);
        FileStorage fileStorage = fileStorageService.save(multipartFile);
        student.setFileStorage(fileStorage);
        studentService.save(student);
        return ResponseEntity.ok(multipartFile.getOriginalFilename() + "file saqlandi");
    }

    @GetMapping("/preview/{hashId}")
    public ResponseEntity<?> previewFile(@PathVariable String hashId) throws IOException {
        FileStorage fileStorage = fileStorageService.findByHashId(hashId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; fileName=\"" + URLEncoder.encode(fileStorage.getName(), StandardCharsets.UTF_8))
                .contentType(MediaType.parseMediaType(fileStorage.getContentType()))
                .contentLength(fileStorage.getFileSize())
                .body(new FileUrlResource(String.format("%s/%s", uploadFolder, fileStorage.getUploadPath())));
    }

    @GetMapping("/download/{hashId}")
    public ResponseEntity<?> downloadFile(@PathVariable String hashId) throws IOException {

        FileStorage fileStorage = fileStorageService.findByHashId(hashId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + URLEncoder.encode(fileStorage.getName(), StandardCharsets.UTF_8))
                .contentType(MediaType.parseMediaType(fileStorage.getContentType()))
                .contentLength(fileStorage.getFileSize())
                .body(new FileUrlResource(String.format("%s/%s", uploadFolder, fileStorage.getUploadPath())));
    }

    @DeleteMapping("/delete/{hashId}")
    public ResponseEntity<?> delete(@PathVariable String hashId){
        try{
            FileStorage fileStorage = fileStorageService.findByHashId(hashId);
            Student student = studentService.findByFileStorageId(fileStorage.getId());
            student.setFileStorage(null);
            studentService.save(student);
        } catch (Exception e){
            e.printStackTrace();
        }
        fileStorageService.delete(hashId);
        return ResponseEntity.ok("File ochirildi");
    }

}
