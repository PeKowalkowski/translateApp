package com.example.translateApp.translateApp.controllers;

import com.example.translateApp.translateApp.entities.NonExistWords;
import com.example.translateApp.translateApp.entities.Words;
import com.example.translateApp.translateApp.exceptions.WordNotFoundException;
import com.example.translateApp.translateApp.services.NonExistWordsService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nonExistWords")
public class NonExistWordsController {

    private NonExistWordsService nonExistWordsService;

    public NonExistWordsController(NonExistWordsService nonExistWordsService) {
        this.nonExistWordsService = nonExistWordsService;
    }

    @GetMapping("/getNonExistWords")
    public ResponseEntity<List<NonExistWords>> getNonExisWords() {
        try {
            List<NonExistWords> nonExistWordsList = nonExistWordsService.getNonExistWords();
            return new ResponseEntity<List<NonExistWords>>(nonExistWordsList, HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getNonExistWordsById/{id}")
    public ResponseEntity<Optional<NonExistWords>> getNonExistWordsById(@PathVariable Long id) {
        Optional<NonExistWords> nonExistWords = Optional.ofNullable(nonExistWordsService.getNonExistWordsById(id)
                .orElseThrow(() -> new WordNotFoundException(id)));
        return ResponseEntity.ok(nonExistWords);
    }

    @GetMapping("/getNonExistWords/{pageNumber}/{pageSize}")
    public ResponseEntity<Page<NonExistWords>> getNonExistWordsPageNoAndPageSize(@PathVariable int pageNumber, @PathVariable int pageSize) {
        Page<NonExistWords> nonExistWordsPage = nonExistWordsService.getNonExistWordsPageNoAndPageSize(pageNumber, pageSize);
        return ResponseEntity.ok(nonExistWordsPage);
    }

    @GetMapping("/amountOfNonExistWords")
    public ResponseEntity<Long> getAmountOfNonExistWords() {
        Long amount = nonExistWordsService.getAmountOfNonExistWords();
        return ResponseEntity.ok(amount);
    }
}
