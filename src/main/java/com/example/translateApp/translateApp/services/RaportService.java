package com.example.translateApp.translateApp.services;

import com.example.translateApp.translateApp.entities.Raport;
import com.example.translateApp.translateApp.repositories.RaportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RaportService {

    private WordsService wordsService;

    private RaportRepository raportRepository;

    private NonExistWordsService nonExistWordsService;
    private Logger logger = LoggerFactory.getLogger(RaportService.class);

    public RaportService(WordsService wordsService, RaportRepository raportRepository, NonExistWordsService nonExistWordsService) {
        this.wordsService = wordsService;
        this.raportRepository = raportRepository;
        this.nonExistWordsService = nonExistWordsService;
    }


    public Raport addRaport(Long polishLength, Long englishLength) {
        Long amountPl = wordsService.getAmountOfEnglishWords();
        Long avgLenPl = wordsService.getAvgLengthOfPolishWords();
        Long polishWordsWithLen = wordsService.getPolishWordsWithLength(polishLength);
        Long amountEng = wordsService.getAmountOfEnglishWords();
        Long avgLenEng = wordsService.getAvgLengthOfEnglishWords();
        Long englishWordsWithLen = wordsService.getEnglishWordsWithLength(englishLength);
        Long amountNonExistWords = nonExistWordsService.getAmountOfNonExistWords();



        Raport raport1 = new Raport(amountPl, avgLenPl, polishWordsWithLen, amountEng, avgLenEng, englishWordsWithLen, amountNonExistWords);
        logger.info("Created raport with word's details.");
        return raportRepository.save(raport1);
    }

    public List<Raport> getRaports() {
        List<Raport> raportList = raportRepository.findAll().stream()
                .map(raport -> {
                    Raport raport1 = new Raport(raport.getId(), raport.getAmountOfPolishWords(), raport.getAverageLengthOfPolishWords(), raport.getPolishWordsWithLength(),
                            raport.getAmountOfEnglishWords(), raport.getAverageLengthOfEnglishWords(), raport.getEnglishWordsWithLength(), raport.getAmountOfNonExistWords());
                    return raport1;
                })
                .collect(Collectors.toList());
        logger.info("Loaded all raports.");
        return raportList;

    }
}
