package com.example.translateApp.translateApp.services;

import com.example.translateApp.translateApp.dtos.WordsDto;
import com.example.translateApp.translateApp.entities.AssignedWord;
import com.example.translateApp.translateApp.entities.Dictionary;
import com.example.translateApp.translateApp.entities.Words;
import com.example.translateApp.translateApp.enums.Language;
import com.example.translateApp.translateApp.exceptions.WordNotFoundException;
import com.example.translateApp.translateApp.mapper.WordsMapper;
import com.example.translateApp.translateApp.repositories.DictionaryRepository;
import com.example.translateApp.translateApp.repositories.NonExistWordsRepository;
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
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {DictionaryServiceTest.class})
class DictionaryServiceTest {

    @Mock
    DictionaryRepository dictionaryRepository;
    @InjectMocks
    DictionaryService dictionaryService;
    public List<Dictionary> dictionaryList;

    @Test
    @Order(1)
    @DisplayName("Should return list of dictionary with size 2")
    void getDictionariesTest() {
        dictionaryList = new ArrayList<Dictionary>();
        Dictionary dictionaries = new Dictionary();
        dictionaries.setId(1L);
        dictionaries.setWords(new Words(1L, "wieza", Language.POLISH, new AssignedWord("tower", Language.ENGLISH)));

        Dictionary dictionaries2 = new Dictionary();
        dictionaries2.setId(2L);
        dictionaries2.setWords(new Words(2L, "zaba", Language.POLISH, new AssignedWord("frog", Language.ENGLISH)));

        dictionaryList.add(dictionaries);
        dictionaryList.add(dictionaries2);

        when(dictionaryRepository.findAll()).thenReturn(dictionaryList);

        List<Dictionary> newDictinaryList = dictionaryService.getDictionaries();

        assertEquals(2, newDictinaryList.size());
        assertNotNull(newDictinaryList);
    }

    @Test
    @Order(2)
    @DisplayName("Should return dictionary with required id")
    public void getDictionaryByIdTest() {
        Dictionary dictionary = new Dictionary();
        dictionary.setId(1L);
        dictionary.setWords(new Words(1L, "wieza", Language.POLISH, new AssignedWord("tower", Language.ENGLISH)));

        Dictionary dictionary2 = new Dictionary();
        dictionary2.setId(2L);
        dictionary2.setWords(new Words(2L, "zaba", Language.POLISH, new AssignedWord("frog", Language.ENGLISH)));

        when(dictionaryRepository.findById(anyLong())).thenReturn(Optional.of(dictionary));

        Optional<Dictionary> existingDictionary = dictionaryService.getDictionariesById(1L);

        assertNotNull(existingDictionary);
        assertThat(existingDictionary.get().getId()).isNotEqualTo(null);
    }
    @Test
    @Order(3)
    @DisplayName("Should throw Exception.")
    public void getDictionaryByIdTestForException() {
        Dictionary dictionary = new Dictionary();
        dictionary.setId(1L);
        dictionary.setWords(new Words(1L, "wieza", Language.POLISH, new AssignedWord("tower", Language.ENGLISH)));

        Dictionary dictionary2 = new Dictionary();
        dictionary2.setId(2L);
        dictionary2.setWords(new Words(2L, "zaba", Language.POLISH, new AssignedWord("frog", Language.ENGLISH)));

        when(dictionaryRepository.findById(1L)).thenReturn(Optional.of(dictionary));

        assertThrows(WordNotFoundException.class, () ->{
            dictionaryService.getDictionariesById(3L);
        });

    }

}