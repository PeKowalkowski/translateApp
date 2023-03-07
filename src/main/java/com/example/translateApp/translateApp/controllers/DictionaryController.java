package com.example.translateApp.translateApp.controllers;

import com.example.translateApp.translateApp.entities.Dictionary;
import com.example.translateApp.translateApp.exceptions.WordNotFoundException;
import com.example.translateApp.translateApp.repositories.DictionaryRepository;
import com.example.translateApp.translateApp.services.AssignedWordService;
import com.example.translateApp.translateApp.services.DictionaryService;
import com.example.translateApp.translateApp.services.PdfGenerator;
import com.example.translateApp.translateApp.services.WordsService;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dictionary/")
public class DictionaryController {

    private DictionaryService dictionaryService;


    private DictionaryRepository dictionaryRepository;


    private WordsService wordsService;

    private AssignedWordService assignedWordService;

    private Logger logger = LoggerFactory.getLogger(DictionaryController.class);

    public DictionaryController(DictionaryService dictionaryService, DictionaryRepository dictionaryRepository, WordsService wordsService, AssignedWordService assignedWordService) {
        this.dictionaryService = dictionaryService;
        this.dictionaryRepository = dictionaryRepository;
        this.wordsService = wordsService;
        this.assignedWordService = assignedWordService;
    }

    @GetMapping("/getDictionaries/{id}")
    public ResponseEntity<Optional<Dictionary>> getDictionariesById(@PathVariable Long id) {
        Optional<Dictionary> dictionary = Optional.ofNullable(dictionaryService.getDictionariesById(id)
                .orElseThrow(() -> new WordNotFoundException(id)));
        return ResponseEntity.ok(dictionary);
    }

    @GetMapping("/getDictionaries")
    public ResponseEntity<List<Dictionary>> getDictionaries() {
        List<Dictionary> dictionaryList = dictionaryService.getDictionaries();
        return ResponseEntity.ok(dictionaryList);
    }
    @GetMapping("/getDictionaries/{pageNumber}/{pageSize}")
    public ResponseEntity<Page<Dictionary>> getDictionariesPageNoAndPageSize(@PathVariable int pageNumber, @PathVariable int pageSize){
        Page<Dictionary> dictionaries = dictionaryService.getDictionariesPageNoAndPageSize( pageNumber,pageSize);
        return ResponseEntity.ok(dictionaries);
    }

    @GetMapping("/exportDictionaryToPdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException{
        response.setContentType("dictionary/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Dictionary> dictionaryList = dictionaryService.getDictionaries();

        PdfGenerator pdfGenerator = new PdfGenerator(dictionaryList);
        pdfGenerator.export(response);
    }



}
