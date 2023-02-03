package com.example.testproject.service;

import com.example.testproject.domain.Student;
import com.example.testproject.repository.StudentRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class ExporterImpl implements ExporterService {
    private final StudentRepository studentRepository;
    @Value("${upload.folder}")
    private String uploadFolder;

    public ExporterImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public void exportExcel(HttpServletResponse response, Long id) throws IOException {
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

        if (id == null) {
            List<Student> students = studentRepository.findAll();
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

        } else {
            Student student = studentRepository.findById(id).orElse(null);
            HSSFRow row1 = sheet.createRow(1);
            assert student != null;
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
        }

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.flush();
        outputStream.close();
    }

    @Override
    public void exportPDF(Student student, HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(25);
        font.setColor(BaseColor.RED);

        Paragraph paragraph = new Paragraph("Resume");
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Image image = Image.getInstance(uploadFolder + "/" + student.getFileStorage().getUploadPath());
        PdfPTable pdfPTable = new PdfPTable(4);
        image.setAlignment(Image.ALIGN_RIGHT);
        pdfPTable.getDefaultCell().setBorder(0);
        pdfPTable.addCell("");
        pdfPTable.addCell("");
        pdfPTable.addCell(student.getDescription());
        pdfPTable.addCell(image);

        document.add(pdfPTable);
        document.add(paragraph);
        PdfPTable table = new PdfPTable(2);
        table.setSpacingAfter(25);
        table.setSpacingBefore(25);

        PdfPCell r1 = new PdfPCell(Phrase.getInstance("First Name"));
        PdfPCell a1 = new PdfPCell(Phrase.getInstance(student.getFirstName()));
        table.addCell(r1);
        table.addCell(a1);

        PdfPCell r2 = new PdfPCell(Phrase.getInstance("Last Name"));
        PdfPCell a2 = new PdfPCell(Phrase.getInstance(student.getLastName()));
        table.addCell(r2);
        table.addCell(a2);

        PdfPCell r3 = new PdfPCell(Phrase.getInstance("Middle Name"));
        PdfPCell a3 = new PdfPCell(Phrase.getInstance(student.getMiddleName()));
        table.addCell(r3);
        table.addCell(a3);

        PdfPCell r4 = new PdfPCell(Phrase.getInstance("University"));
        PdfPCell a4 = new PdfPCell(Phrase.getInstance(student.getField_of_studies().getUniversity().getName()));
        table.addCell(r4);
        table.addCell(a4);

        PdfPCell r5 = new PdfPCell(Phrase.getInstance("Field of Study"));
        PdfPCell a5 = new PdfPCell(Phrase.getInstance(student.getField_of_studies().getName()));
        table.addCell(r5);
        table.addCell(a5);

        PdfPCell r6 = new PdfPCell(Phrase.getInstance("Study Start Date"));
        PdfPCell a6 = new PdfPCell(Phrase.getInstance(student.getStudy_start_date()));
        table.addCell(r6);
        table.addCell(a6);

        PdfPCell r7 = new PdfPCell(Phrase.getInstance("Study End Date"));
        PdfPCell a7 = new PdfPCell(Phrase.getInstance(student.getStudy_end_date()));
        table.addCell(r7);
        table.addCell(a7);


        document.add(table);
        document.close();
    }

}
