package com.example.translateApp.translateApp.services;

import com.example.translateApp.translateApp.entities.AssignedWord;
import com.example.translateApp.translateApp.repositories.AssignedWordsRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AssignedWordService {

    private AssignedWordsRepository assignedWordsRepository;

    private Logger logger = LoggerFactory.getLogger(AssignedWordService.class);

    public AssignedWordService(AssignedWordsRepository assignedWordsRepository) {
        this.assignedWordsRepository = assignedWordsRepository;
    }

    public Optional<AssignedWord> getWordById(Long id) {
        Optional<AssignedWord> assignedWord = assignedWordsRepository.findById(id);
        return assignedWord;
    }
}
