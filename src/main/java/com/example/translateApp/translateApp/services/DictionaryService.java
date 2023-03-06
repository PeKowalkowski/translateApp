package com.example.translateApp.translateApp.services;

import com.example.translateApp.translateApp.entities.Dictionary;
import com.example.translateApp.translateApp.repositories.DictionaryRepository;
import com.example.translateApp.translateApp.repositories.EnglishWordRepository;
import com.example.translateApp.translateApp.repositories.PolishWordRepository;
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
public class DictionaryService {

    private DictionaryRepository dictionaryRepository;
    private EnglishWordRepository englishWordRepository;
    private PolishWordRepository polishWordRepository;

    private PolishWordService polishWordService;


    private Logger logger = LoggerFactory.getLogger(DictionaryService.class);


    public DictionaryService(DictionaryRepository dictionaryRepository, EnglishWordRepository englishWordRepository,
                             PolishWordRepository polishWordRepository, PolishWordService polishWordService) {
        this.dictionaryRepository = dictionaryRepository;
        this.englishWordRepository = englishWordRepository;
        this.polishWordRepository = polishWordRepository;
        this.polishWordService = polishWordService;
    }

    public Dictionary save(Dictionary dictionary) {
        dictionaryRepository.save(dictionary);
        return dictionary;
    }

    public List<Dictionary> getDictionaries() {
        List<Dictionary> dictionaryList = dictionaryRepository.findAll().stream()
                .map(dictionary -> {
                    Dictionary dictionary1 = new Dictionary(dictionary.getId(), dictionary.getPolishWords(), dictionary.getEnglishWords());
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
                    Dictionary dictionary1 = new Dictionary(dictionary.getId(), dictionary.getPolishWords(), dictionary.getEnglishWords());
                    return dictionary1;
                })
                .collect(Collectors.toList());
        logger.info("Loaded all records from dictionary.");
        return dictionaryList;
    }

    public Optional<Dictionary> getDictionariesById(Long id) {
        Optional<Dictionary> dictionary = dictionaryRepository.findById(id);
        logger.info("Loaded record from dictionary with id : " + id + ".");
        return dictionary;
    }

    public List<Dictionary> getByEnglishWord(Long englishWordId) {
        List<Dictionary> dictionaryList = dictionaryRepository.getByEnglishWord(englishWordId).stream()
                .map(dictionary -> {
                    Dictionary dictionary1 = new Dictionary(dictionary.getPolishWords());
                    return dictionary1;
                })
                .collect(Collectors.toList());
        logger.info("Translated english word : " + englishWordId);
        return dictionaryList;
    }
    /*public List<Dictionary> getByEnglishSentence(List<Dictionary> englishWordId) {
        List<Dictionary> dictionaryList = dictionaryRepository.getByEnglishSentence(englishWordId).stream()
                .map(dictionary -> {
                    Dictionary dictionary1 = new Dictionary(dictionary.getPolishWordsList());
                    return dictionary1;
                })
                .collect(Collectors.toList());
        return dictionaryList;
    }*/

    public List<Dictionary> getByPolishWord(Long polishWordId) {
        List<Dictionary> dictionaryList = dictionaryRepository.getByPolishWord(polishWordId).stream()
                .map(dictionary -> {
                    Dictionary dictionary1 = new Dictionary(dictionary.getEnglishWords());
                    return dictionary1;
                })
                .collect(Collectors.toList());
        logger.info("Translated polish word : " + polishWordId);
        return dictionaryList;
    }

    public List<Dictionary> getDictionaryRaportWithPolishWords() {
        List<Dictionary> dictionaryList = dictionaryRepository.findAll().stream()
                .map(dictionary -> {
                    Dictionary dictionary1 = new Dictionary(dictionary.getPolishWords());
                    return dictionary1;
                })
                .collect(Collectors.toList());
        logger.info("Loaded all records from dictionary with polish words. Quantity of polish words : " + dictionaryList.size());
        return dictionaryList;

    }
    public List<Dictionary> getDictionaryRaportWithEnglishWords() {
        List<Dictionary> dictionaryList = dictionaryRepository.findAll().stream()
                .map(dictionary -> {
                    Dictionary dictionary1 = new Dictionary(dictionary.getEnglishWords());
                    return dictionary1;
                })
                .collect(Collectors.toList());
        logger.info("Loaded all records from dictionary with english words. Quantity of english words : " + dictionaryList.size());
        return dictionaryList;

    }


}
