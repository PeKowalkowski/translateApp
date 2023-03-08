package com.example.translateApp.translateApp.services;

import com.example.translateApp.translateApp.dtos.WordsDto;
import com.example.translateApp.translateApp.entities.NonExistWords;
import com.example.translateApp.translateApp.entities.Words;
import com.example.translateApp.translateApp.mapper.WordsMapper;
import com.example.translateApp.translateApp.mapper.WordsMapper2;
import com.example.translateApp.translateApp.repositories.NonExistWordsRepository;
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

    private NonExistWordsRepository nonExistWordsRepository;

    private NonExistWordsService nonExistWordsService;


    public WordsService(WordsRepository wordsRepository, WordsMapper wordsMapper, NonExistWordsRepository nonExistWordsRepository, NonExistWordsService nonExistWordsService) {
        this.wordsRepository = wordsRepository;
        this.wordsMapper = wordsMapper;
        this.nonExistWordsRepository = nonExistWordsRepository;
        this.nonExistWordsService = nonExistWordsService;
    }

    public WordsDto addWordsWithAssignedWord(WordsDto wordsDto) {
        wordsRepository.save(wordsMapper.wordsDtoToWords(wordsDto));
        return wordsDto;

    }

    public List<Words> getWords() {
        List<Words> wordsList = wordsRepository.findAll().stream()
                .map(words -> {
                    Words words1 = new Words(words.getId(), words.getWord(), words.getLanguage(), words.getAssignedWord());
                    return words1;
                })
                .collect(Collectors.toList());
        logger.info("Loaded all words from dictionary.");
        return wordsList;

    }

    public Page<Words> getWordsPageNoAndPageSize(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Words> wordsPage = (Page<Words>) wordsRepository.findAll(pageable).stream()
                .map(words -> {
                    Words words1 = new Words(words.getId(), words.getWord());
                    return words1;
                })
                .collect(Collectors.toList());
        logger.info("Loaded all records from words.");
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
        List<NonExistWords> nonExistWordsList = nonExistWordsRepository.getByWord(word).stream()
                .map(nonExistWords -> {
                    NonExistWords nonExistWords1 = new NonExistWords(nonExistWords.getWord());
                    return nonExistWords1;
                })
                .collect(Collectors.toList());
        if (!wordsList.contains(word) && !nonExistWordsList.contains(word)) {
            NonExistWords nonExistWords = new NonExistWords(word);
            nonExistWordsRepository.save(nonExistWords);
        }
        logger.info("Loaded row from word : " + word);
        return wordsList;
    }

    public String[] getBySentence(String sentence) {
        String[] splitString = sentence.split(" ");
        List<Words> wordsList = wordsRepository.findAll().stream()
                .map(words -> {
                    Words words1 = new Words(words.getWord());
                    return words1;
                })
                .collect(Collectors.toList());
        for (int i = 0; i <= splitString.length - 1; i++) {
            if (wordsList.contains(splitString[i])) {
                getByWord(splitString[i]);

            } else if (!wordsList.contains(splitString[i])) {
                NonExistWords nonExistWords = new NonExistWords(splitString[i]);
                nonExistWordsRepository.save(nonExistWords);
            }
        }
        return splitString;
    }

    public Long getPolishWordsWithLength(Long length) {
        Long count = wordsRepository.getPolishWordsByLength(length);

        return count;
    }

    public Long getEnglishWordsWithLength(Long length) {
        Long count = wordsRepository.getEnglishWordsByLength(length);
        return count;
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

    public String getRaport(Long polishWordLength, Long englishWordLength) {
        getAmountOfPolishWords();
        getPolishWordsWithLength(polishWordLength);
        getAvgLengthOfPolishWords();
        getAmountOfEnglishWords();
        getEnglishWordsWithLength(englishWordLength);
        getAvgLengthOfEnglishWords();
        nonExistWordsService.getAmountOfNonExistWords();

        return ("Amount of Polish words = " + getAmountOfPolishWords() + ". Polish words with length (" + polishWordLength + ") = " + getPolishWordsWithLength(polishWordLength) +
                ". Average length of Polish words = " + getAvgLengthOfPolishWords() + ". Amount of English words = " + getAmountOfEnglishWords() + ". English words with length (" + englishWordLength + ") = "
                + getEnglishWordsWithLength(englishWordLength) + ". Average length of English words = " + getAvgLengthOfEnglishWords() + ". Amount of non exist words = " + nonExistWordsService.getAmountOfNonExistWords());

    }
    /*public String getRaport(*//*Long polishWordLength, Long englishWordLength*//*) {
        getAmountOfPolishWords();

        getAvgLengthOfPolishWords();
        getAmountOfEnglishWords();

        getAvgLengthOfEnglishWords();
        nonExistWordsService.getAmountOfNonExistWords();

        return ("Amount of Polish words = " + getAmountOfPolishWords() +
                ". Average length of Polish words = " + getAvgLengthOfPolishWords() + ". Amount of English words = " + getAmountOfEnglishWords() +
                 ". Average length of English words = " + getAvgLengthOfEnglishWords() + ". Amount of non exist words = " + nonExistWordsService.getAmountOfNonExistWords());

    }*/


}
