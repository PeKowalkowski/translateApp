package com.example.translateApp.translateApp.services;

import com.example.translateApp.translateApp.dtos.WordsDto;
import com.example.translateApp.translateApp.entities.Words;
import com.example.translateApp.translateApp.mapper.WordsMapper;
import com.example.translateApp.translateApp.mapper.WordsMapper2;
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
public class WordsService {

    private Logger logger = LoggerFactory.getLogger(WordsService.class);

    private WordsRepository wordsRepository;

    private WordsMapper2 wordsMapper2;

    private WordsMapper wordsMapper;

    public WordsService(WordsRepository wordsRepository, WordsMapper wordsMapper) {
        this.wordsRepository = wordsRepository;
        this.wordsMapper = wordsMapper;
    }

    public WordsDto addWordsWithAssignedWord(WordsDto wordsDto) {
        wordsRepository.save(wordsMapper.wordsDtoToWords(wordsDto));
        return wordsDto;

    }

    public Page<Words> getWordsPageNoAndPageSize(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Words> wordsPage = (Page<Words>) wordsRepository.findAll(pageable).stream()
                .map(words -> {
                    Words words1 = new Words(words.getId(), words.getWord(), words.getLanguage(), words.getAssignedWord());
                    return words1;
                })
                .collect(Collectors.toList());
        logger.info("Loaded all records from dictionary.");
        return wordsPage;
    }


    public Optional<Words> getWordById(Long id) {
        Optional<Words> word = wordsRepository.findById(id);
        logger.info("Loaded word with id : " + id);
        return word;
    }

    public List<Words> getByWord(String word) {
        List<Words> wordsList = wordsRepository.getByWord(word).stream()
                .map(words -> {
                    Words words1 = new Words(words.getId(), words.getWord(), words.getLanguage(), words.getAssignedWord());
                    return words1;
                })
                .collect(Collectors.toList());
        logger.info("Loaded row from word : " + word);
        return wordsList;
    }


    public List<Words> getPolishWordsWithLength(Long length) {
        int amount = 0;
        List<Words> polishWordsList = wordsRepository.getPolishWordsByLength(length).stream()
                .map(words -> {
                    Words polishWords1 = new Words(words.getId(), words.getWord());
                    return polishWords1;
                })
                .collect(Collectors.toList());
        for (int i = 0; i <= polishWordsList.size() - 1; i++) {
            int size = amount + polishWordsList.size();
            logger.info("Amount of English words with length (" + length + ") = " + size);
        }
        return polishWordsList;
    }

    public List<Words> getEnglishWordsWithLength(Long length) {
        int amount = 0;
        List<Words> englishWordsList = wordsRepository.getEnglishWordsByLength(length).stream()
                .map(words -> {
                    Words englishWords1 = new Words(words.getId(), words.getWord());
                    return englishWords1;
                })
                .collect(Collectors.toList());
        for (int i = 0; i <= englishWordsList.size() - 1; i++) {
            int size = amount + englishWordsList.size();
            logger.info("Amount of English words with length (" + length + ") = " + size);
        }
        return englishWordsList;
    }

    public Long getAmountOfPolishWords() {
        Long count = wordsRepository.getAmountOfPolishWords();
        logger.info("Amount of Polish words : " + count);
        return count;
    }

    public Long getAmountOfEnglishWords() {
        Long count = wordsRepository.getAmountOfEnglishWords();
        logger.info("Amount of English words : " + count);
        return count;
    }

    public Long getAvgLengthOfPolishWords() {
        Long avg = wordsRepository.getAvgLengthOfPolishWords();
        logger.info("Average length of Polish words : " + avg);
        return avg;
    }

    public Long getAvgLengthOfEnglishWords() {
        Long avg = wordsRepository.getAvgLengthOfEnglishWords();
        logger.info("Average length of English words : " + avg);
        return avg;
    }

    public String getPolishRaport(Long length) {
        getAmountOfPolishWords();
        getPolishWordsWithLength(length);
        getAvgLengthOfPolishWords();

        return "Amount of Polish words = " + getAmountOfPolishWords() + ". Polish words with length (" + length + ") = " + getPolishWordsWithLength(length) +
                ". Average length of Polish words = " + getAvgLengthOfPolishWords();
    }

    public String getEnglishRaport(Long length) {
        getAmountOfEnglishWords();
        getEnglishWordsWithLength(length);
        getAvgLengthOfEnglishWords();

        return "Amount of English words = " + getAmountOfEnglishWords() + ". English words with length (" + length + ") = " + getEnglishWordsWithLength(length) +
                ". Average length of English words = " + getAvgLengthOfEnglishWords();
    }
}
