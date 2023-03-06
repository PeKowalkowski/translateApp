package com.example.translateApp.translateApp.controllers;

import com.example.translateApp.translateApp.dtos.PolishWordsDto;
import com.example.translateApp.translateApp.entities.Dictionary;
import com.example.translateApp.translateApp.entities.PolishWords;
import com.example.translateApp.translateApp.exceptions.WordNotFoundException;
import com.example.translateApp.translateApp.repositories.PolishWordRepository;
import com.example.translateApp.translateApp.services.EnglishWordService;
import com.example.translateApp.translateApp.services.PolishWordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/polishWords/")
public class PolishWordController {

    private PolishWordService polishWordService;

    private EnglishWordService englishWordService;

    private PolishWordRepository polishWordRepository;

    public PolishWordController(PolishWordService polishWordService, EnglishWordService englishWordService, PolishWordRepository polishWordRepository) {
        this.polishWordService = polishWordService;
        this.englishWordService = englishWordService;
        this.polishWordRepository = polishWordRepository;
    }

    @PostMapping("/addPolishWord")
    ResponseEntity<PolishWordsDto> addPolishWord(@RequestBody PolishWordsDto polishWordsDto){
        PolishWordsDto polishWordsDto1 = polishWordService.addPolishWord(polishWordsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(polishWordsDto1);

    }
    @GetMapping("/words")
    public ResponseEntity<List<PolishWords>> getPolishWords() {
        List<PolishWords> polishWordsList = polishWordService.getPolishWords();
        return ResponseEntity.ok(polishWordsList);
    }
    @GetMapping("/words/{id}")
    public ResponseEntity<Optional<PolishWords>> getPolishWordById(@PathVariable Long id) {
        Optional<PolishWords> polishWords = Optional.ofNullable(polishWordService.getPolishWordById(id)
                .orElseThrow(() -> new WordNotFoundException(id)));
        return ResponseEntity.ok(polishWords);
    }
    @GetMapping("/polishWord/{word}")
    public ResponseEntity<List<PolishWords>> getByWord(@PathVariable String word) {
        List<PolishWords> polishWordsList = polishWordService.getByWord(word);
        return ResponseEntity.ok(polishWordsList);
    }
    @GetMapping("/translatePolishSentence/{sentence}")
    public ResponseEntity<List<String>> translateByPolishSentence(@PathVariable String sentence) {
        List<String> polishSentence = polishWordService.translatePolishSentence(sentence);
        return ResponseEntity.ok(polishSentence);
    }
    @GetMapping("/polishWord/words/{length}")
    public ResponseEntity<List<PolishWords>> getPolishWordsWithLength(@PathVariable Long length) {
        List<PolishWords> polishWordsList = polishWordService.getPolishWordsWithLength(length);
        return ResponseEntity.ok(polishWordsList);
    }
    @GetMapping("/polishWord/amountOfPolishWords/")
    public ResponseEntity<Long> getAmountOfPolishWords() {
        Long amount = polishWordService.getAmountOfPolishWords();
        return ResponseEntity.ok(amount);
    }

    @GetMapping("/polishWord/averageLength")
    public ResponseEntity<Long> getAvgLengthOfPolishWords(){
        Long avg = polishWordService.getAvgLengthOfPolishWords();
        return ResponseEntity.ok(avg);
    }

    @GetMapping("polishWord/getRaport/{length}")
    public ResponseEntity<String> getPolishRaport(@PathVariable Long length){
        String raport = polishWordService.getPolishRaport(length);
        return ResponseEntity.ok(raport);
    }


}
