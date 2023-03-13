package com.example.translateApp.translateApp.services;

import com.example.translateApp.translateApp.entities.Raport;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PdfGenerator {


    private List<Raport> raportList;

    public PdfGenerator(List<Raport> raportList) {
        this.raportList = raportList;
    }

    public PdfGenerator(String raport) {
    }

    private void writeTableHeader(PdfPTable pdfTable) {

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);


        cell.setPhrase(new Phrase("ID", font));
        pdfTable.addCell(cell);

        cell.setPhrase(new Phrase("Amount Of Polish Words", font));
        pdfTable.addCell(cell);

        cell.setPhrase(new Phrase("Average Length Of Polish Words", font));
        pdfTable.addCell(cell);

        cell.setPhrase(new Phrase("Polish Words With Length", font));
        pdfTable.addCell(cell);

        cell.setPhrase(new Phrase("Amount Of English Words", font));
        pdfTable.addCell(cell);

        cell.setPhrase(new Phrase("Average Length Of English Words", font));
        pdfTable.addCell(cell);

        cell.setPhrase(new Phrase("English Words With Length", font));
        pdfTable.addCell(cell);

        cell.setPhrase(new Phrase("Amount Of Non ExistWords", font));
        pdfTable.addCell(cell);


    }

    private void writeTableData(PdfPTable table) {
        for (Raport raport : raportList) {


            table.addCell(String.valueOf(raport.getId()));
            table.addCell(String.valueOf(raport.getAmountOfPolishWords()));
            table.addCell(String.valueOf(raport.getAverageLengthOfPolishWords()));
            table.addCell(String.valueOf(raport.getPolishWordsWithLength()));
            table.addCell(String.valueOf(raport.getAmountOfEnglishWords()));
            table.addCell(String.valueOf(raport.getAverageLengthOfEnglishWords()));
            table.addCell(String.valueOf(raport.getEnglishWordsWithLength()));
            table.addCell(String.valueOf(raport.getAmountOfNonExistWords()));

        }
    }


    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Raport", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(8);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1f, 2f, 2f, 2f, 2f, 2f, 2f, 2.2f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();
    }
}
