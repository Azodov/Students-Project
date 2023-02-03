package com.example.testproject.rest;

import com.example.testproject.domain.Student;
import com.example.testproject.service.StudentService;
import com.example.testproject.utils.PdfExporter;
import com.itextpdf.text.DocumentException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class PdfController {
    private final StudentService studentService;
    private final PdfExporter pdfExp;

    public PdfController(StudentService studentService, PdfExporter pdfExp) {
        this.studentService = studentService;
        this.pdfExp = pdfExp;
    }

    @GetMapping("/pdf/{id}")
    public void export(HttpServletResponse response, @PathVariable Long id) throws DocumentException, IOException {
        Student student = studentService.findById(id);
        pdfExp.createCell(student, response);
    }
}
