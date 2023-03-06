package com.example.translateApp.translateApp.controllers;

import com.example.translateApp.translateApp.dtos.EnglishWordsDto;
import com.example.translateApp.translateApp.entities.EnglishWords;
import com.example.translateApp.translateApp.entities.PolishWords;
import com.example.translateApp.translateApp.exceptions.WordNotFoundException;
import com.example.translateApp.translateApp.services.EnglishWordService;
import com.example.translateApp.translateApp.services.PolishWordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/englishWords/")
public class EnglishWordController {

    private EnglishWordService englishWordService;

    private PolishWordService polishWordService;

    public EnglishWordController(EnglishWordService englishWordService, PolishWordService polishWordService) {
        this.englishWordService = englishWordService;
        this.polishWordService = polishWordService;
    }

    @PostMapping("/addEnglishWord")
    ResponseEntity<EnglishWordsDto> addEnglishWord(@RequestBody EnglishWordsDto englishWordsDto){
        EnglishWordsDto englishWordsDto1 = englishWordService.addEnglishWord(englishWordsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(englishWordsDto1);

    }
    @GetMapping("/words")
    public ResponseEntity<List<EnglishWords>> getEnglishWords() {
        List<EnglishWords> englishWordsList = englishWordService.getEnglishWords();
        return ResponseEntity.ok(englishWordsList);
    }

    @GetMapping("/words/{id}")
    public ResponseEntity<Optional<EnglishWords>> getEnglishWordById(@PathVariable Long id) {
        Optional<EnglishWords> englishWords = Optional.ofNullable(englishWordService.getEnglishWordById(id)
                .orElseThrow(() -> new WordNotFoundException(id)));
        return ResponseEntity.ok(englishWords);
    }


    @GetMapping("/englishWord/{word}")
    public ResponseEntity<List<EnglishWords>> getByWord(@PathVariable String word) {

        List<EnglishWords> englishWordsList = englishWordService.getByWord(word);
        return ResponseEntity.ok(englishWordsList);
    }

    @GetMapping("/englishWord/words/{length}")
    public ResponseEntity<List<EnglishWords>> getEnglishWordsWithLength(@PathVariable Long length) {
        List<EnglishWords> englishWordsList = englishWordService.getEnglishWordsWithLength(length);
        return ResponseEntity.ok(englishWordsList);
    }
    @GetMapping("/englishWord/amountOfEnglishWords/")
    public ResponseEntity<Long> getAmountOfEnglishWords() {
        Long amount = englishWordService.getAmountOfEnglishWords();
        return ResponseEntity.ok(amount);
    }

    @GetMapping("/englishWord/averageLength")
    public ResponseEntity<Long> getAvgLengthOfEnglishWords(){
        Long avg = englishWordService.getAvgLengthOfEnglishWords();
        return ResponseEntity.ok(avg);
    }

    @GetMapping("englishWord/getRaport/{length}")
    public ResponseEntity<String> getEnglishRaport(@PathVariable Long length){
        String raport = englishWordService.getEnglishRaport(length);
        return ResponseEntity.ok(raport);
    }



}
