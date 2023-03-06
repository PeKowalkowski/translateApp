package com.example.translateApp.translateApp.services;

import com.example.translateApp.translateApp.entities.Dictionary;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PdfGenerator {

    private List<Dictionary> dictionaryList;

    public PdfGenerator(List<Dictionary> dictionaryList) {
        this.dictionaryList = dictionaryList;
    }

    private void writeTableHeader(PdfPTable pdfTable){

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("ID", font));

        pdfTable.addCell(cell);

        cell.setPhrase(new Phrase("ENGLISH_WORDS_ID", font));
        pdfTable.addCell(cell);

        cell.setPhrase(new Phrase("POLISH_WORDS_ID", font));
        pdfTable.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {
        for (Dictionary dictionary : dictionaryList) {
            table.addCell(String.valueOf(dictionary.getId()));
            table.addCell(String.valueOf(dictionary.getEnglishWords()));
            table.addCell(String.valueOf(dictionary.getPolishWords()));
        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException{
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Words", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();
    }
}
