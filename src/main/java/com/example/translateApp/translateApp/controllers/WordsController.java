package com.example.translateApp.translateApp.controllers;

import com.example.translateApp.translateApp.dtos.WordsDto;
import com.example.translateApp.translateApp.entities.Words;
import com.example.translateApp.translateApp.exceptions.WordNotFoundException;
import com.example.translateApp.translateApp.services.AssignedWordService;
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

    private AssignedWordService assignedWordService;

    public WordsController(WordsService wordsService, AssignedWordService assignedWordService) {
        this.wordsService = wordsService;
        this.assignedWordService = assignedWordService;
    }

    @PostMapping("/addWordWithAssignedWord")
    ResponseEntity<WordsDto> addWordWithAssignedWord(@RequestBody WordsDto wordsDto) {
        WordsDto wordsDto1 = wordsService.addWordsWithAssignedWord(wordsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(wordsDto1);
    }

    @GetMapping("/getWords")
    public ResponseEntity<List<Words>> getWords() {
        try {
            List<Words> wordsList = wordsService.getWords();
            return new ResponseEntity<List<Words>>(wordsList, HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getWords/{pageNumber}/{pageSize}")
    public ResponseEntity<Page<Words>> getWordsPageNoAndPageSize(@PathVariable int pageNumber, @PathVariable int pageSize) {
        Page<Words> wordsPage = wordsService.getWordsPageNoAndPageSize(pageNumber, pageSize);
        return ResponseEntity.ok(wordsPage);
    }

   @GetMapping("/getWordById/{id}")
    public ResponseEntity<Optional<Words>> getWordById(@PathVariable Long id) {
        Optional<Words> word = Optional.ofNullable(wordsService.getWordById(id)
                .orElseThrow(() -> new WordNotFoundException(id)));
        return ResponseEntity.ok(word);
    }


    @GetMapping("/translate/{word}")
    public ResponseEntity<String> translateWord(@PathVariable String word) {
        String translate = wordsService.getByWord(word);
        return ResponseEntity.ok(translate);
    }

    @GetMapping("/translateSentence/{sentence}")
    public ResponseEntity<List<String>> translateSentence(@PathVariable String sentence) {
        List<String> wordsList = wordsService.getBySentence(sentence);
        return ResponseEntity.ok(wordsList);
    }


    @GetMapping("/polishWord/{length}")
    public ResponseEntity<Long> getPolishWordsWithLength(@PathVariable Long length) {
        Long amount = wordsService.getPolishWordsWithLength(length);
        return ResponseEntity.ok(amount);
    }

    @GetMapping("/englishWords/{length}")
    public ResponseEntity<Long> getEnglishWordsWithLength(@PathVariable Long length) {
        Long amount = wordsService.getEnglishWordsWithLength(length);
        return ResponseEntity.ok(amount);
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
    public ResponseEntity<Long> getAvgLengthOfPolishWords() {
        Long avg = wordsService.getAvgLengthOfPolishWords();
        return ResponseEntity.ok(avg);
    }

    @GetMapping("/englishWord/averageLength")
    public ResponseEntity<Long> getAvgLengthOfEnglishWords() {
        Long avg = wordsService.getAvgLengthOfEnglishWords();
        return ResponseEntity.ok(avg);
    }

    @GetMapping("getRaport/{polishLength}/{englishLength}")
    public ResponseEntity<String> getPolishRaport(@PathVariable Long polishLength, @PathVariable Long englishLength) {
        String raport = wordsService.getRaport(polishLength, englishLength);
        return ResponseEntity.ok(raport);
    }


}
