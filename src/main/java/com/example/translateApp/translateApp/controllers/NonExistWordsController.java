package com.example.translateApp.translateApp.controllers;

import com.example.translateApp.translateApp.entities.NonExistWords;
import com.example.translateApp.translateApp.services.NonExistWordsService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nonExistWords")
public class NonExistWordsController {

    private NonExistWordsService nonExistWordsService;

    public NonExistWordsController(NonExistWordsService nonExistWordsService) {
        this.nonExistWordsService = nonExistWordsService;
    }

    @GetMapping("/getNonExistWords")
    public ResponseEntity<List<NonExistWords>> getNonExisWords() {
        List<NonExistWords> nonExistWordsList = nonExistWordsService.getNonExistWords();
        return ResponseEntity.ok(nonExistWordsList);
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
