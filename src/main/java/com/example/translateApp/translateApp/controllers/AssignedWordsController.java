package com.example.translateApp.translateApp.controllers;

import com.example.translateApp.translateApp.entities.AssignedWord;
import com.example.translateApp.translateApp.services.AssignedWordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asignedWordsController")
public class AssignedWordsController {

    private AssignedWordService assignedWordService;

    public AssignedWordsController(AssignedWordService assignedWordService) {
        this.assignedWordService = assignedWordService;
    }
    @GetMapping("/getAssignedWords")
    public ResponseEntity<List<AssignedWord>> getAssignedWords() {
        try {
            List<AssignedWord> assignedWordList = assignedWordService.getAssignedWords();
            return new ResponseEntity<List<AssignedWord>>(assignedWordList, HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAssignedWordById/{id}")
    public ResponseEntity<Optional<AssignedWord>> getAssignedWordById(@PathVariable Long id) {
        Optional<AssignedWord> assignedWord = assignedWordService.getAssignedWordById(id);
        return ResponseEntity.ok(assignedWord);
    }
}
