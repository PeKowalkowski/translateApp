package com.example.translateApp.translateApp.controllers;

import com.example.translateApp.translateApp.entities.Dictionary;
import com.example.translateApp.translateApp.entities.NonExistWords;
import com.example.translateApp.translateApp.entities.Words;
import com.example.translateApp.translateApp.exceptions.WordNotFoundException;
import com.example.translateApp.translateApp.repositories.DictionaryRepository;
import com.example.translateApp.translateApp.repositories.WordsRepository;
import com.example.translateApp.translateApp.services.DictionaryService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dictionary/")
public class DictionaryController {

    private DictionaryService dictionaryService;


    private DictionaryRepository dictionaryRepository;


    private WordsRepository wordsRepository;

    public DictionaryController(DictionaryService dictionaryService, DictionaryRepository dictionaryRepository, WordsRepository wordsRepository) {
        this.dictionaryService = dictionaryService;
        this.dictionaryRepository = dictionaryRepository;
        this.wordsRepository = wordsRepository;
    }

    @GetMapping("/getDictionaries/{id}")
    public ResponseEntity<Optional<Dictionary>> getDictionariesById(@PathVariable Long id) {
        Optional<Dictionary> dictionary = Optional.ofNullable(dictionaryService.getDictionariesById(id)
                .orElseThrow(() -> new WordNotFoundException(id)));
        return ResponseEntity.ok(dictionary);
    }

    @GetMapping("/getDictionaries")
    public ResponseEntity<List<Dictionary>> getDictionaries() {
        try {
            List<Dictionary> dictionaryList = dictionaryService.getDictionaries();
            return new ResponseEntity<List<Dictionary>>(dictionaryList, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        /*List<Dictionary> dictionaryList = dictionaryService.getDictionaries();
        return ResponseEntity.ok(dictionaryList);*/
    }

    @GetMapping("/getDictionaries/{pageNumber}/{pageSize}")
    public ResponseEntity<Page<Dictionary>> getDictionariesPageNoAndPageSize(@PathVariable int pageNumber, @PathVariable int pageSize) {
        Page<Dictionary> dictionaries = dictionaryService.getDictionariesPageNoAndPageSize(pageNumber, pageSize);
        return ResponseEntity.ok(dictionaries);
    }

    @PutMapping("/addWordToDictionary/{wordId}")
    public ResponseEntity<Dictionary> addWordToDictionary(@PathVariable Long wordId) {
        Dictionary dictionary = dictionaryService.addWordToDictionary(wordId);
        return ResponseEntity.status(HttpStatus.CREATED).body(dictionary);

    }


}
