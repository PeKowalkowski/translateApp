package com.example.translateApp.translateApp.controllers;

import com.example.translateApp.translateApp.entities.AssignedWord;
import com.example.translateApp.translateApp.services.AssignedWordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/asignedWordsController")
public class AssignedWordsController {

    private AssignedWordService assignedWordService;

    public AssignedWordsController(AssignedWordService assignedWordService) {
        this.assignedWordService = assignedWordService;
    }

    @GetMapping("/getWordById/{id}")
    public ResponseEntity<Optional<AssignedWord>> getWordById(@PathVariable Long id) {
        Optional<AssignedWord> assignedWord = assignedWordService.getWordById(id);
        return ResponseEntity.ok(assignedWord);
    }
}
