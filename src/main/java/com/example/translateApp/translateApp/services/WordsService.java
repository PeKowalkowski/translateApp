package com.example.translateApp.translateApp.services;

import com.example.translateApp.translateApp.dtos.WordsDto;
import com.example.translateApp.translateApp.entities.AssignedWord;
import com.example.translateApp.translateApp.entities.NonExistWords;
import com.example.translateApp.translateApp.entities.Words;
import com.example.translateApp.translateApp.exceptions.WordNotFoundException;
import com.example.translateApp.translateApp.mapper.WordsMapper;
import com.example.translateApp.translateApp.mapper.WordsMapper2;
import com.example.translateApp.translateApp.repositories.AssignedWordsRepository;
import com.example.translateApp.translateApp.repositories.NonExistWordsRepository;
import com.example.translateApp.translateApp.repositories.WordsRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static org.aspectj.asm.internal.CharOperation.indexOf;

@Service
@Slf4j
public class WordsService {

    private Logger logger = LoggerFactory.getLogger(WordsService.class);

    private WordsRepository wordsRepository;

    private WordsMapper2 wordsMapper2;

    private WordsMapper wordsMapper;

    private NonExistWordsRepository nonExistWordsRepository;

    private NonExistWordsService nonExistWordsService;

    private AssignedWordService assignedWordService;

    private AssignedWordsRepository assignedWordsRepository;


    public WordsService(WordsRepository wordsRepository, WordsMapper wordsMapper, NonExistWordsRepository nonExistWordsRepository,
                        NonExistWordsService nonExistWordsService, AssignedWordService assignedWordService, AssignedWordsRepository assignedWordsRepository) {
        this.wordsRepository = wordsRepository;
        this.wordsMapper = wordsMapper;
        this.nonExistWordsRepository = nonExistWordsRepository;
        this.nonExistWordsService = nonExistWordsService;
        this.assignedWordService = assignedWordService;
        this.assignedWordsRepository = assignedWordsRepository;
    }

    public WordsDto addWordsWithAssignedWord(WordsDto wordsDto) {
        List<Words> wordsList = wordsRepository.findAll().stream()
                .map(words -> {
                    Words words1 = new Words(words.getWord(), words.getLanguage());
                    return words1;

                })
                .collect(Collectors.toList());
        if (!wordsList.contains(wordsDto.getWord())) {
            wordsRepository.save(wordsMapper.wordsDtoToWords(wordsDto));
        }
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
        Optional<Words> word = Optional.ofNullable(wordsRepository.findById(id).orElseThrow(() -> new WordNotFoundException(id)));
        logger.info("Loaded word with id : " + id);
        return word;
    }

    public String getByWord(String word) {

        Optional<Words> wordsOptional = wordsRepository.getByWord2(word);
        Optional<NonExistWords> nonExistWordsOptional = nonExistWordsRepository.getByWord2(word);

        logger.info("words optional " + wordsOptional);

        if (wordsOptional.isEmpty() && nonExistWordsOptional.isEmpty()){
            NonExistWords nonExistWords = new NonExistWords(word);
            logger.info("Added new word to nonExistWords(" + word + ").");
           return String.valueOf(nonExistWordsRepository.save(nonExistWords));

        }if(wordsOptional.isEmpty() && nonExistWordsOptional.isPresent()){
            logger.info("(" + word + ") is exist in nonExistWords.");
        }else {
            logger.info("Translated " + word + " (" + wordsOptional.get().getLanguage() + ") to "
                    + wordsOptional.get().getTranslation() + " (" + wordsOptional.get().getAssignedWord().getLanguage() + ").");
        }

        return wordsOptional.get().getTranslation();
    }

    public List<String> getBySentence(String sentence) {
        String[] splitString = sentence.split(" ");
        List<String> splitStringList = Arrays.asList(splitString);
        List<String> nowaLista = new ArrayList<>();
        /*Optional<Words> wordsOptional = wordsRepository.getByWord2(Arrays.toString(splitStringList));
        Optional<NonExistWords> nonExistWordsOptional = nonExistWordsRepository.getByWord2(Arrays.toString(splitStringList));*/

        logger.info("words optional " + splitStringList);
        List<Words> wordsList = wordsRepository.findAll();


        for(int i = 0; i <= wordsList.size() -1 ; i++){
            logger.info("Word list " + wordsList.get(i).getWord());
            for(int j = 0; j <= splitStringList.size() -1 ;j++){
                logger.info("Spring string list " + splitStringList.get(j));
                if (wordsList.contains(splitStringList.get(i))){
                    nowaLista.add(splitStringList.get(i));
                }
            }


        }
        logger.info("Nowa lita " + nowaLista);
/*

        List<Words> wordsList = wordsRepository.findAll().stream()
                .map(words -> {
                    Words words1 = new Words(words.getId(), words.getWord(), words.getLanguage(), words.getAssignedWord());
                    return words1;
                })
                .collect(Collectors.toList());
*/

/*
        for (int i = 0; i <= splitStringList.size() - 1; i++) {
            List<Words> getByWord = wordsRepository.getByWord(splitStringList.get(i)).stream()
                    .map(words -> {
                        Words words1 = new Words(words.getAssignedWord().getWord());
                        return words1;
                    })
                    .collect(Collectors.toList());
            if (!wordsList.contains(splitStringList.get(i))) {
                NonExistWords nonExistWords = new NonExistWords(splitString[i]);
                nonExistWordsRepository.save(nonExistWords);
            }
            nowaLista.add(getByWord.toString());
            logger.info("Translate : " + splitStringList.get(i) + " to : " + getByWord);
        }*/
       return splitStringList;
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

}
