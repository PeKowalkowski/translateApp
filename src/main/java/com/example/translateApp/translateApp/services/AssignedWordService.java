package com.example.translateApp.translateApp.services;

import com.example.translateApp.translateApp.entities.AssignedWord;
import com.example.translateApp.translateApp.entities.NonExistWords;
import com.example.translateApp.translateApp.entities.Words;
import com.example.translateApp.translateApp.repositories.AssignedWordsRepository;
import com.example.translateApp.translateApp.repositories.WordsRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AssignedWordService {

    private AssignedWordsRepository assignedWordsRepository;

    private WordsRepository wordsRepository;

    private Logger logger = LoggerFactory.getLogger(AssignedWordService.class);

    public AssignedWordService(AssignedWordsRepository assignedWordsRepository, WordsRepository wordsRepository) {
        this.assignedWordsRepository = assignedWordsRepository;
        this.wordsRepository = wordsRepository;
    }

    public Optional<AssignedWord> getWordById(Long id) {
        Optional<AssignedWord> assignedWord = assignedWordsRepository.findById(id);
        logger.info("Loaded assigned word with id : " + id);
        return assignedWord;
    }


}
