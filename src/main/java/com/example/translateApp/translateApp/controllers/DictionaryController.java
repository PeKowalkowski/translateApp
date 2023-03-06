package com.example.translateApp.translateApp.controllers;

import com.example.translateApp.translateApp.entities.Dictionary;
import com.example.translateApp.translateApp.entities.EnglishWords;
import com.example.translateApp.translateApp.entities.NonExistWords;
import com.example.translateApp.translateApp.entities.PolishWords;
import com.example.translateApp.translateApp.exceptions.WordNotFoundException;
import com.example.translateApp.translateApp.repositories.DictionaryRepository;
import com.example.translateApp.translateApp.repositories.EnglishWordRepository;
import com.example.translateApp.translateApp.repositories.PolishWordRepository;
import com.example.translateApp.translateApp.services.*;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dictionary/")
public class DictionaryController {

    private DictionaryService dictionaryService;

    private EnglishWordService englishWordService;

    private PolishWordService polishWordService;

    private DictionaryRepository dictionaryRepository;

    private EnglishWordRepository englishWordRepository;

    private PolishWordRepository polishWordRepository;

    private NonExistWordsService nonExistWordsService;

    private Logger logger = LoggerFactory.getLogger(DictionaryController.class);

    public DictionaryController(DictionaryService dictionaryService, EnglishWordService englishWordService, PolishWordService polishWordService, DictionaryRepository dictionaryRepository, EnglishWordRepository englishWordRepository,
                                PolishWordRepository polishWordRepository, NonExistWordsService nonExistWordsService) {
        this.dictionaryService = dictionaryService;
        this.englishWordService = englishWordService;
        this.polishWordService = polishWordService;
        this.dictionaryRepository = dictionaryRepository;
        this.englishWordRepository = englishWordRepository;
        this.polishWordRepository = polishWordRepository;
        this.nonExistWordsService = nonExistWordsService;
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

    @GetMapping("/getDictionaries/raportWithPolishWords")
    public ResponseEntity<List<Dictionary>> getDictionaryRaportWithPolishWords() {
        List<Dictionary> dictionaryList = dictionaryService.getDictionaryRaportWithPolishWords();
        return ResponseEntity.ok(dictionaryList);
    }
    @GetMapping("/getDictionaries/raportWithEnglishWords")
    public ResponseEntity<List<Dictionary>> getDictionaryRaportWithEnglishWords() {
        List<Dictionary> dictionaryList = dictionaryService.getDictionaryRaportWithEnglishWords();
        return ResponseEntity.ok(dictionaryList);
    }

    @GetMapping("/translateEnglishWord/{englishWordId}")
    public ResponseEntity<List<Dictionary>> translateByEnglishWord(@PathVariable Long englishWordId) {
        /*if(englishWordId == null){
            NonExistWords nonExistWords = nonExistWordsService.save(englishWordId);
        }*/
        List<Dictionary> dictionaryList = dictionaryService.getByEnglishWord(englishWordId);
        return ResponseEntity.ok(dictionaryList);
    }



    /*@GetMapping("/translateEnglishSentence/{englishWordId}")
    public ResponseEntity<List<Dictionary>> translateByEnglishSentence(@PathVariable List<Dictionary> englishWordId ) {
        List<Dictionary> dictionary = new ArrayList<>(englishWordId);
        List<Dictionary> dictionaryList = dictionaryService.getByEnglishSentence(dictionary);
        return ResponseEntity.ok(dictionaryList);
    }*/
    @GetMapping("/translatePolishWord/{polishWordId}")
    public ResponseEntity<List<Dictionary>> translateByPolishWord(@PathVariable Long polishWordId) {
        List<Dictionary> dictionaryList = dictionaryService.getByPolishWord(polishWordId);
        return ResponseEntity.ok(dictionaryList);
    }


    @PutMapping("/{polishWordId}")
    public Dictionary addPolishToDictionary( @PathVariable Long polishWordId /*String polishWordId*/,
                                                          @RequestBody Dictionary dictionary) {

        PolishWords polishWords = polishWordService.getPolishWordById(polishWordId).get();
/*
        PolishWords polishWords =  polishWordService.getByWord(polishWordId);
*/

        dictionary.setPolishWords(polishWords);
        logger.info("Added polish word with id : " + polishWordId + " to dictionary."  );
        return dictionaryService.save(dictionary);
    }

    @PutMapping("/{englishWordId}")
    public Dictionary addEnglishToDictionary( @PathVariable Long englishWordId /*String englishWordId*/,
                                             @RequestBody Dictionary dictionary) {
        EnglishWords englishWords = englishWordService.getEnglishWordById(englishWordId).get();
/*
        EnglishWords englishWords =  englishWordService.getByWord(englishWordId);
*/

        dictionary.setEnglishWords(englishWords);
        logger.info("Added english word with id : " + englishWordId + " to dictionary."  );
        return dictionaryService.save(dictionary);
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
