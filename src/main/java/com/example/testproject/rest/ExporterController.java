package com.example.testproject.rest;

import com.example.testproject.domain.Student;
import com.example.testproject.repository.StudentRepository;
import com.example.testproject.service.StudentService;
import com.example.testproject.utils.ExcelExporter;
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
public class ExporterController {
    private final StudentService studentService;
    private final StudentRepository studentRepository;
    private final PdfExporter pdfExp;
    private final ExcelExporter excelExporter;

    public ExporterController(StudentService studentService, StudentRepository studentRepository, PdfExporter pdfExp, ExcelExporter excelExporter) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
        this.pdfExp = pdfExp;
        this.excelExporter = excelExporter;
    }

    @GetMapping("/pdf/{id}")
    public void export(HttpServletResponse response, @PathVariable Long id) throws DocumentException, IOException {
        Student student = studentService.findById(id);
        pdfExp.createCell(student, response);
    }

    @GetMapping("/excel")
    public void excel(HttpServletResponse response) throws IOException{
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=students.xlsx";
        response.setHeader(headerKey, headerValue);
        excelExporter.export(response, null);
    }

    @GetMapping("/excel/{id}")
    public void excel(HttpServletResponse response, @PathVariable Long id) throws IOException{
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=students.xlsx";
        response.setHeader(headerKey, headerValue);
        excelExporter.export(response, id);
    }
}
