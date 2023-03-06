package com.example.translateApp.translateApp.services;

import com.example.translateApp.translateApp.dtos.PolishWordsDto;
import com.example.translateApp.translateApp.entities.Dictionary;
import com.example.translateApp.translateApp.entities.PolishWords;
import com.example.translateApp.translateApp.mapper.PolishWordsMapper;
import com.example.translateApp.translateApp.repositories.PolishWordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class PolishWordService {

    private PolishWordRepository polishWordRepository;


    private PolishWordsMapper polishWordsMapper;

    private Logger logger = LoggerFactory.getLogger(PolishWordService.class);

    public PolishWordService(PolishWordRepository polishWordRepository, PolishWordsMapper polishWordsMapper) {
        this.polishWordRepository = polishWordRepository;
        this.polishWordsMapper = polishWordsMapper;
    }


    public PolishWordsDto addPolishWord(PolishWordsDto polishWordsDto) {
        PolishWords polishWords = polishWordsMapper.polishWordsMapperDtoToEntity(polishWordsDto);
        PolishWords polishWords1 = polishWordRepository.save(polishWords);
        logger.info("Added new word : " + polishWords);
        return polishWordsMapper.polishWordsMapperEntityToDto(polishWords1);
    }
    public List<PolishWords> getPolishWords() {
        List<PolishWords> polishWordsList = polishWordRepository.findAll().stream()
                .map(polishWords -> {
                    PolishWords polishWords1 = new PolishWords(polishWords.getId(), polishWords.getWord());
                    return polishWords1;
                })
                .collect(Collectors.toList());

        logger.info("Loaded all records from polish words." + polishWordRepository.findAll());
        return polishWordsList;
    }

    public Optional<PolishWords> getPolishWordById(Long id) {
        Optional<PolishWords> polishWords = polishWordRepository.findById(id);
        logger.info("Loaded row from polish words with id : " + id + ". " + polishWordRepository.findById(id));
        return polishWords;
    }
    public List<PolishWords> getByWord(String word) {
        List<PolishWords> polishWordsList = polishWordRepository.getByWord(word).stream()
                .map(polishWords -> {
                    PolishWords polishWords1 = new PolishWords(polishWords.getId(),polishWords.getWord());
                    return polishWords1;
                })
                .collect(Collectors.toList());
        logger.info("Loaded row from word : " + word);
        return polishWordsList;
    }


    public List<PolishWords> getPolishWordsWithLength(Long length) {
        int amount = 0;
        List<PolishWords> polishWordsList = polishWordRepository.getByLength(length).stream()
                .map(polishWords -> {
                    PolishWords polishWords1 = new PolishWords(polishWords.getId(),polishWords.getWord());
                    return polishWords1;
                })
                .collect(Collectors.toList());
        for(int i = 0; i<= polishWordsList.size() -1; i++){
            int size = amount + polishWordsList.size();
            logger.info("Amount of polish words with length (" + length + ") = " + size);
        }
        return polishWordsList;
    }



    public Long getAmountOfPolishWords() {
        Long count = polishWordRepository.getAmountOfPolishWords();
        logger.info("Amount of Polish words : " + count);
        return count;
    }

    public Long getAvgLengthOfPolishWords() {
        Long avg = polishWordRepository.getAvgLengthOfPolishWords();
        logger.info("Average length of Polish words : " + avg);
        return avg;
    }

    public List<String> translatePolishSentence(String sentence) {
        List<PolishWords> polishWordsList = polishWordRepository.findAll();
        String sentenceString = sentence;
        String[] sentenceArray = sentenceString.split(" ");
        for(int i = 0; i<=sentenceArray.length-1 ; i++){
            if(polishWordsList.contains(sentenceArray[i])){
                polishWordsList.stream()
                        .map(polishWords -> {
                            PolishWords polishWords1 = new PolishWords(polishWords.getEnglishWords());
                            return polishWords1;
                        })
                        .collect(Collectors.toList());
            }
        }
        return List.of(sentenceArray);
    }

    public String getPolishRaport(Long length) {
        getAmountOfPolishWords();
        getPolishWordsWithLength(length);
        getAvgLengthOfPolishWords();

        return "Amount of polish words = " + getAmountOfPolishWords() + ". Polish words with length (" + length + ") = " + getPolishWordsWithLength(length) +
        ". Average length of polish words = " + getAvgLengthOfPolishWords();
    }

}
