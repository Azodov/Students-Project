package com.example.testproject.utils;

import com.example.testproject.domain.Student;
import com.example.testproject.repository.StudentRepository;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelExporter {
    private final StudentRepository studentRepository;

    public ExcelExporter(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void exportExcel(HttpServletResponse response) throws IOException {
        List<Student> students = studentRepository.findAll();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Students");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("First Name");
        row.createCell(2).setCellValue("Last Name");
        row.createCell(3).setCellValue("Middle Name");
        row.createCell(4).setCellValue("Gender");
        row.createCell(5).setCellValue("Description");
        row.createCell(6).setCellValue("University");
        row.createCell(7).setCellValue("Field of studies");
        row.createCell(8).setCellValue("Date of birth");
        row.createCell(9).setCellValue("Study start date");
        row.createCell(10).setCellValue("Study end date");


        int i = 1;
        for (Student student : students) {
            HSSFRow row1 = sheet.createRow(i);
            row1.createCell(0).setCellValue(student.getId());
            row1.createCell(1).setCellValue(student.getFirstName());
            row1.createCell(2).setCellValue(student.getLastName());
            row1.createCell(3).setCellValue(student.getMiddleName());
            row1.createCell(4).setCellValue(student.getGender());
            row1.createCell(5).setCellValue(student.getDescription());
            row1.createCell(6).setCellValue(student.getField_of_studies().getUniversity().getName());
            row1.createCell(7).setCellValue(student.getField_of_studies().getName());
            row1.createCell(8).setCellValue(student.getDateOfBirth());
            row1.createCell(9).setCellValue(student.getStudy_start_date());
            row1.createCell(10).setCellValue(student.getStudy_end_date());
            i++;
        }


        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.flush();
        outputStream.close();
    }
}
