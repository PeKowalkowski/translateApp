package com.example.translateApp.translateApp.services;

import com.example.translateApp.translateApp.entities.NonExistWords;
import com.example.translateApp.translateApp.entities.Words;
import com.example.translateApp.translateApp.exceptions.WordNotFoundException;
import com.example.translateApp.translateApp.repositories.NonExistWordsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NonExistWordsService {

    private NonExistWordsRepository nonExistWordsRepository;
    private Logger logger = LoggerFactory.getLogger(NonExistWordsService.class);


    public NonExistWordsService(NonExistWordsRepository nonExistWordsRepository) {
        this.nonExistWordsRepository = nonExistWordsRepository;
    }

    public Page<NonExistWords> getNonExistWordsPageNoAndPageSize(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<NonExistWords> nonExistWordsPage = (Page<NonExistWords>) nonExistWordsRepository.findAll(pageable).stream()
                .map(nonExistWords -> {
                    NonExistWords nonExistWords1 = new NonExistWords(nonExistWords.getId(), nonExistWords.getWord());
                    return nonExistWords1;
                })
                .collect(Collectors.toList());
        logger.info("Loaded all records from Non Exist Words.");
        return nonExistWordsPage;
    }

    public List<NonExistWords> getNonExistWords() {
        List<NonExistWords> nonExistWordsList = nonExistWordsRepository.findAll().stream()
                .map(nonExistWords -> {
                    NonExistWords nonExistWords1 = new NonExistWords(nonExistWords.getId(), nonExistWords.getWord());
                    return nonExistWords1;
                })
                .collect(Collectors.toList());
        logger.info("Loaded all words from Non Exist Words.");
        return nonExistWordsList;

    }
    public Optional<NonExistWords> getNonExistWordsById(Long id) {
        Optional<NonExistWords> nonExistWords = Optional.ofNullable(nonExistWordsRepository.findById(id).orElseThrow(() -> new WordNotFoundException(id)));
        logger.info("Loaded non exist word with id : " + id);
        return nonExistWords;
    }

    public Long getAmountOfNonExistWords() {
        Long count = nonExistWordsRepository.getAmountOfNonExistWords();
        logger.info("Amount of Non Exist Words : " + count);
        return count;
    }



}
