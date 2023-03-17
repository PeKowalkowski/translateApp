/*
package com.example.translateApp.translateApp.services;

import com.example.translateApp.translateApp.dtos.WordsDto;
import com.example.translateApp.translateApp.entities.AssignedWord;
import com.example.translateApp.translateApp.entities.Raport;
import com.example.translateApp.translateApp.entities.Words;
import com.example.translateApp.translateApp.enums.Language;
import com.example.translateApp.translateApp.repositories.NonExistWordsRepository;
import com.example.translateApp.translateApp.repositories.RaportRepository;
import com.example.translateApp.translateApp.repositories.WordsDtoRepository;
import com.example.translateApp.translateApp.repositories.WordsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {RaportServiceTest.class})
class RaportServiceTest {

    @Mock
    RaportRepository raportRepository ;

    @Mock
    WordsService wordsService;

    @Mock
    NonExistWordsService nonExistWordsService;


    @InjectMocks
    RaportService raportService;
    public List<Raport> raportList;

    */
/*@Test
    @Order(1)
    @DisplayName("Should save new raport database.")
    public void addRaportTest() {
        Raport raport = new Raport();
        raport.setId(1L);
        raport.setAmountOfPolishWords(2L);
        raport.setAverageLengthOfPolishWords(4L);
        raport.setPolishWordsWithLength(4L);
        raport.setAmountOfEnglishWords(2L);
        raport.setAverageLengthOfEnglishWords(4L);
        raport.setEnglishWordsWithLength(4L);

        Words words = new Words();
        words.setId(1L);
        words.setWord("pies");
        words.setLanguage(Language.POLISH);
        words.setAssignedWord(new AssignedWord("tower", Language.ENGLISH));

        Words words2 = new Words();
        words2.setId(2L);
        words2.setWord("ptak");
        words2.setLanguage(Language.POLISH);
        words2.setAssignedWord(new AssignedWord("tower", Language.ENGLISH));

        Words words3 = new Words();
        words3.setId(3L);
        words3.setWord("bird");
        words3.setLanguage(Language.ENGLISH);
        words3.setAssignedWord(new AssignedWord("ptak", Language.POLISH));

        Words words4 = new Words();
        words4.setId(4L);
        words4.setWord("book");
        words4.setLanguage(Language.ENGLISH);
        words4.setAssignedWord(new AssignedWord("ksiazka", Language.POLISH));

        List<Words> wordsList = new ArrayList<>();

        Long polisWordsLength = 4L;

        Long englishWordsLenth = 4L;

        *//*
*/
/*Long polishWordsSize = wordsList.(0) + wordsList.get(1);
        Long englishWordsSize = (long) list.size();*//*
*/
/*


        when(raportRepository.save(raport)).thenReturn(raport);

        Raport newRaport = raportService.addRaport(polisWordsLength,englishWordsLenth);

        assertEquals(newRaport, raportService.addRaport(polisWordsLength,englishWordsLenth));


        assertThat(newRaport.getId()).isEqualTo(1L);
        assertThat(newRaport.getAmountOfPolishWords()).isEqualTo(2L);
        assertThat(newRaport.getAverageLengthOfPolishWords()).isEqualTo(4L);
        assertThat(newRaport.getPolishWordsWithLength()).isEqualTo(4L);
        assertThat(newRaport.getAmountOfEnglishWords()).isEqualTo(2L);
        assertThat(newRaport.getAverageLengthOfEnglishWords()).isEqualTo(4L);
        assertThat(newRaport.getEnglishWordsWithLength()).isEqualTo(4L);
    }*//*

}*/
