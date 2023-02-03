package com.example.testproject.utils;

import com.example.testproject.domain.Student;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class PDFExporter {
    private void writeToTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.BLACK);
        cell.setPadding(10);
        Font font = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.ITALIC);
        font.setColor(BaseColor.LIGHT_GRAY);

        cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("First Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Last Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Middle Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("University", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Field of study", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Study started date", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Study end date", font));
        table.addCell(cell);
    }
    private void writeToTableData(PdfPTable table, Student student){
        table.addCell(String.valueOf(student.getId()));
        table.addCell(student.getFirstName());
        table.addCell(student.getLastName());
        table.addCell(student.getMiddleName());
        table.addCell(student.getField_of_studies().getUniversity().getName());
        table.addCell(student.getField_of_studies().getName());
        table.addCell(student.getStudy_start_date());
        table.addCell(student.getStudy_end_date());
    }
    public void export(HttpServletResponse response, Student student) throws IOException, DocumentException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font =FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(12);
        font.setColor(BaseColor.RED);

        Paragraph paragraph = new Paragraph("Resume", font);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(paragraph);

        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{0.4f, 0.9f, 0.9f, 0.9f, 1f, 1f, 1f, 1f});
        table.setSpacingBefore(5);

        writeToTableHeader(table);
        writeToTableData(table, student);

        document.add(table);
        document.close();
    }
}
