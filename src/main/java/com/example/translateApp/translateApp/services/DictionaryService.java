package com.example.translateApp.translateApp.services;

import com.example.translateApp.translateApp.entities.Dictionary;
import com.example.translateApp.translateApp.entities.Words;
import com.example.translateApp.translateApp.exceptions.WordNotFoundException;
import com.example.translateApp.translateApp.repositories.DictionaryRepository;
import com.example.translateApp.translateApp.repositories.WordsRepository;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class DictionaryService {

    private DictionaryRepository dictionaryRepository;

    private WordsRepository wordsRepository;


    private Logger logger = LoggerFactory.getLogger(DictionaryService.class);


    public DictionaryService(DictionaryRepository dictionaryRepository, WordsRepository wordsRepository) {
        this.dictionaryRepository = dictionaryRepository;
        this.wordsRepository = wordsRepository;
    }

    public List<Dictionary> getDictionaries() {
        List<Dictionary> dictionaryList = dictionaryRepository.findAll().stream()
                .map(dictionary -> {
                    Dictionary dictionary1 = new Dictionary(dictionary.getId(), dictionary.getWords());

                    return dictionary1;
                })
                .collect(Collectors.toList());
        logger.info("Loaded all words from dictionary.");
        return dictionaryList;

    }

    public Page<Dictionary> getDictionariesPageNoAndPageSize(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Dictionary> dictionaryList = (Page<Dictionary>) dictionaryRepository.findAll(pageable).stream()
                .map(dictionary -> {
                    Dictionary dictionary1 = new Dictionary(dictionary.getId(), dictionary.getWords());
                    return dictionary1;
                })
                .collect(Collectors.toList());
        logger.info("Loaded all records from dictionary.");
        return dictionaryList;
    }

    public Optional<Dictionary> getDictionariesById(Long id) {
        Optional<Dictionary> dictionary = Optional.ofNullable(dictionaryRepository.findById(id).orElseThrow(() -> new WordNotFoundException(id)));
        logger.info("Loaded record from dictionary with id : " + id + ".");
        return dictionary;
    }

    public Dictionary addWordToDictionary(Long wordId) {
        Words words = wordsRepository.findById(wordId).get();
        Dictionary dictionary = new Dictionary();

        dictionary.setWords(words);
        dictionary.setWord(words.getWord());
        dictionary.setTranslate(words.getAssignedWord().getWord());

        logger.info("Added new word to dictionary :" + words.getWord() + ", with translation : " + words.getAssignedWord().getWord());
        return dictionaryRepository.save(dictionary);
    }
}
