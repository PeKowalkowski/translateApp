package com.example.translateApp.translateApp.services;

import com.example.translateApp.translateApp.dtos.EnglishWordsDto;
import com.example.translateApp.translateApp.entities.EnglishWords;
import com.example.translateApp.translateApp.mapper.EnglishWordsMapper;
import com.example.translateApp.translateApp.repositories.EnglishWordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnglishWordService {


    private EnglishWordRepository englishWordRepository;

    private EnglishWordsMapper englishWordsMapper;
    private Logger logger = LoggerFactory.getLogger(EnglishWordService.class);

    public EnglishWordService(EnglishWordRepository englishWordRepository, EnglishWordsMapper englishWordsMapper) {
        this.englishWordRepository = englishWordRepository;
        this.englishWordsMapper = englishWordsMapper;
    }

    public EnglishWordsDto addEnglishWord(EnglishWordsDto englishWordsDto) {
        EnglishWords englishWords = englishWordsMapper.englishWordsMapperDtoToEntity(englishWordsDto);
        EnglishWords englishWords1 = englishWordRepository.save(englishWords);
        logger.info("Added new word : " + englishWords);
        return englishWordsMapper.englishWordsMapperEntityToDto(englishWords1);
    }

    public List<EnglishWords> getEnglishWords() {
        List<EnglishWords> englishWordsList = englishWordRepository.findAll().stream()
                .map(englishWords -> {
                    EnglishWords englishWords1 = new EnglishWords(englishWords.getId(), englishWords.getWord(), englishWords.getPolishWords());
                    return englishWords1;
                })
                .collect(Collectors.toList());
        logger.info("Loaded all records from english words. " + englishWordRepository.findAll());
        return englishWordsList;
    }

    public Optional<EnglishWords> getEnglishWordById(Long id) {
        Optional<EnglishWords> englishWords = englishWordRepository.findById(id);
        logger.info("Loaded row from english words with id : " + id + ". " + englishWordRepository.findById(id));
        return englishWords;
    }

    public List<EnglishWords> getByWord(String word) {
        List<EnglishWords> englishWordsList = englishWordRepository.getByWord(word).stream()
                .map(englishWords -> {
                    EnglishWords englishWords1 = new EnglishWords(englishWords.getId(), englishWords.getWord());
                    return englishWords1;
                })
                .collect(Collectors.toList());
        logger.info("Loaded row from word : " + word);
        return englishWordsList;
    }

    public List<EnglishWords> getEnglishWordsWithLength(Long length) {
        int ammmount = 0;
        List<EnglishWords> englishWordsList = englishWordRepository.getByLength(length).stream()
                .map(englishWords -> {
                    EnglishWords englishWords1 = new EnglishWords(englishWords.getId(), englishWords.getWord());
                    return englishWords1;
                })
                .collect(Collectors.toList());
        for (int i = 0; i <= englishWordsList.size() - 1; i++) {
            int size = ammmount + englishWordsList.size();
            logger.info("Ammount of english words with length (" + length + ") = " + size);
        }
        return englishWordsList;
    }

    public Long getAmountOfEnglishWords() {
        Long count = englishWordRepository.getAmountOfEnglishWords();
        logger.info("Ammount of English words : " + count);
        return count;
    }

    public Long getAvgLengthOfEnglishWords() {
        Long avg = englishWordRepository.getAvgLengthOfEnglishWords();
        logger.info("Average length of English words : " + avg);
        return avg;
    }


    public String getEnglishRaport(Long length) {
        getAmountOfEnglishWords();
        getEnglishWordsWithLength(length);
        getAvgLengthOfEnglishWords();

        return "Amount of english words = " + getAmountOfEnglishWords() + ". English words with length (" + length + ") = " + getEnglishWordsWithLength(length) +
                ". Average length of english words = " + getAvgLengthOfEnglishWords();
    }
}
