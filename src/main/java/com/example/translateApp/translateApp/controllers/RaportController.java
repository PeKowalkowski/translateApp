package com.example.translateApp.translateApp.controllers;

import com.example.translateApp.translateApp.entities.Raport;
import com.example.translateApp.translateApp.services.PdfGenerator;
import com.example.translateApp.translateApp.services.RaportService;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/raport/")
public class RaportController {

    private RaportService raportService;

    public RaportController(RaportService raportService) {
        this.raportService = raportService;
    }

    @PostMapping("/addRaport/{polishLength}/{englishLength}")
    private Raport addRaport(@PathVariable Long polishLength, @PathVariable Long englishLength) {
        return raportService.addRaport(polishLength,englishLength);
    }

    @GetMapping("/getRaports")
    public ResponseEntity<List<Raport>> getRaports() {
        List<Raport> raportList = raportService.getRaports();
        return ResponseEntity.ok(raportList);
    }

    @GetMapping("/exportRaportToPdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("dictionary/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Raport> raportList = raportService.getRaports();

        PdfGenerator pdfGenerator = new PdfGenerator(raportList);
        pdfGenerator.export(response);
    }
}
