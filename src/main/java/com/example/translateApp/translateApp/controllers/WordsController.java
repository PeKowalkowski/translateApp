package com.example.translateApp.translateApp.controllers;

import com.example.translateApp.translateApp.dtos.WordsDto;
import com.example.translateApp.translateApp.entities.Words;
import com.example.translateApp.translateApp.exceptions.WordNotFoundException;
import com.example.translateApp.translateApp.services.WordsService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/words/")
public class WordsController {

    private WordsService wordsService;

    public WordsController(WordsService wordsService) {
        this.wordsService = wordsService;
    }

    @PostMapping("/addWordWithAssignedWord")
    ResponseEntity<WordsDto> addWordWithAssignedWord(@RequestBody WordsDto wordsDto){
        WordsDto wordsDto1 = wordsService.addWordsWithAssignedWord(wordsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(wordsDto1);

    }
    @GetMapping("/getWords/{pageNumber}/{pageSize}")
    public ResponseEntity<Page<Words>> getDictionariesPageNoAndPageSize(@PathVariable int pageNumber, @PathVariable int pageSize){
        Page<Words> words = wordsService.getWordsPageNoAndPageSize( pageNumber,pageSize);
        return ResponseEntity.ok(words);
    }
    @GetMapping("/getWordById/{id}")
    public ResponseEntity<Optional<Words>> getWordById(@PathVariable Long id) {
        Optional<Words> word = Optional.ofNullable(wordsService.getWordById(id)
                .orElseThrow(() -> new WordNotFoundException(id)));
        return ResponseEntity.ok(word);
    }
    @GetMapping("/translate/{word}")
    public ResponseEntity<List<Words>> translateWord(@PathVariable String word) {
        List<Words> wordsList = wordsService.getByWord(word);
        return ResponseEntity.ok(wordsList);
    }
    @GetMapping("/polishWord/{length}")
    public ResponseEntity<List<Words>> getPolishWordsWithLength(@PathVariable Long length) {
        List<Words> polishWordsList = wordsService.getPolishWordsWithLength(length);
        return ResponseEntity.ok(polishWordsList);
    }
    @GetMapping("/englishWords/{length}")
    public ResponseEntity<List<Words>> getEnglishWordsWithLength(@PathVariable Long length) {
        List<Words> englishWordsList = wordsService.getEnglishWordsWithLength(length);
        return ResponseEntity.ok(englishWordsList);
    }

    @GetMapping("/polishWord/amountOfPolishWords/")
    public ResponseEntity<Long> getAmountOfPolishWords() {
        Long amount = wordsService.getAmountOfPolishWords();
        return ResponseEntity.ok(amount);
    }

    @GetMapping("/englishWords/amountOfEnglishWords/")
    public ResponseEntity<Long> getAmountOfEnglishWords() {
        Long amount = wordsService.getAmountOfEnglishWords();
        return ResponseEntity.ok(amount);
    }
    @GetMapping("/polishWord/averageLength")
    public ResponseEntity<Long> getAvgLengthOfPolishWords(){
        Long avg = wordsService.getAvgLengthOfPolishWords();
        return ResponseEntity.ok(avg);
    }
    @GetMapping("/englishWord/averageLength")
    public ResponseEntity<Long> getAvgLengthOfEnglishWords(){
        Long avg = wordsService.getAvgLengthOfEnglishWords();
        return ResponseEntity.ok(avg);
    }
    @GetMapping("polishWord/getPolishRaport/{length}")
    public ResponseEntity<String> getPolishRaport(@PathVariable Long length){
        String raport = wordsService.getPolishRaport(length);
        return ResponseEntity.ok(raport);
    }
    @GetMapping("englishWord/getEnglishRaport/{length}")
    public ResponseEntity<String> getEnglishRaport(@PathVariable Long length){
        String raport = wordsService.getEnglishRaport(length);
        return ResponseEntity.ok(raport);
    }

}
