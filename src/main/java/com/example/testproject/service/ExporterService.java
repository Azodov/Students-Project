package com.example.testproject.service;

import com.example.testproject.domain.Student;
import com.itextpdf.text.DocumentException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ExporterService {
    void exportExcel(HttpServletResponse response, Long id) throws IOException;

    void exportPDF(Student student, HttpServletResponse response) throws DocumentException, IOException;


}
