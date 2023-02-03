package com.example.testproject.utils;

import com.example.testproject.domain.Student;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class PdfExp {
    @Value("${upload.folder}")
    private String uploadFolder;

    public void createCell(Student student, HttpServletResponse response) throws DocumentException, IOException {

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Font font =FontFactory.getFont(FontFactory.HELVETICA_BOLD);
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
        PdfPCell r1 = new PdfPCell(Phrase.getInstance("First Name"));
        PdfPCell r2 = new PdfPCell(Phrase.getInstance("Last Name"));
        PdfPCell r3 = new PdfPCell(Phrase.getInstance("Middle Name"));
        PdfPCell r4 = new PdfPCell(Phrase.getInstance("University"));
        PdfPCell r5 = new PdfPCell(Phrase.getInstance("Field of Study"));
        PdfPCell r6 = new PdfPCell(Phrase.getInstance("Study Start Date"));
        PdfPCell r7 = new PdfPCell(Phrase.getInstance("Study End Date"));

        PdfPCell a1 = new PdfPCell(Phrase.getInstance(student.getFirstName()));
        PdfPCell a2 = new PdfPCell(Phrase.getInstance(student.getLastName()));
        PdfPCell a3 = new PdfPCell(Phrase.getInstance(student.getMiddleName()));
        PdfPCell a4 = new PdfPCell(Phrase.getInstance(student.getField_of_studies().getUniversity().getName()));
        PdfPCell a5 = new PdfPCell(Phrase.getInstance(student.getField_of_studies().getName()));
        PdfPCell a6 = new PdfPCell(Phrase.getInstance(student.getStudy_start_date()));
        PdfPCell a7 = new PdfPCell(Phrase.getInstance(student.getStudy_end_date()));



        PdfPTable table = new PdfPTable(2);
        table.setSpacingAfter(25);
        table.setSpacingBefore(25);
        table.addCell(r1);
        table.addCell(a1);
        table.addCell(r2);
        table.addCell(a2);
        table.addCell(r3);
        table.addCell(a3);
        table.addCell(r4);
        table.addCell(a4);
        table.addCell(r5);
        table.addCell(a5);
        table.addCell(r6);
        table.addCell(a6);
        table.addCell(r7);
        table.addCell(a7);


        document.add(table);
        document.close();
    }

}
