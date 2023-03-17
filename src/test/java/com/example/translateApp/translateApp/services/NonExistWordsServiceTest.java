package com.example.translateApp.translateApp.services;

import com.example.translateApp.translateApp.entities.AssignedWord;
import com.example.translateApp.translateApp.entities.NonExistWords;
import com.example.translateApp.translateApp.entities.Words;
import com.example.translateApp.translateApp.enums.Language;
import com.example.translateApp.translateApp.mapper.WordsMapper;
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

@SpringBootTest(classes = {NonExistWordsServiceTest.class})
class NonExistWordsServiceTest {

    @Mock
    NonExistWordsRepository nonExistWordsRepository;
    @InjectMocks
    NonExistWordsService nonExistWordsService;
    public List<NonExistWords> nonExistWordsList;


    @Test
    @Order(1)
    @DisplayName("Should return list of non exist words with size 2")
    void getNonExistWordsTest() {
        NonExistWords nonExistWords = new NonExistWords();
        nonExistWords.setId(1L);
        nonExistWords.setWord("wieza");


        NonExistWords nonExistWords2 = new NonExistWords();
        nonExistWords2.setId(2L);
        nonExistWords2.setWord("dom");

        List<NonExistWords> nonExistWordsList1 = new ArrayList<>();
        nonExistWordsList1.add(nonExistWords);
        nonExistWordsList1.add(nonExistWords2);

        when(nonExistWordsRepository.findAll()).thenReturn(nonExistWordsList1);

        List<NonExistWords> nonExistWordsList2 = nonExistWordsService.getNonExistWords();

        assertEquals(2, nonExistWordsList2.size());
        assertNotNull(nonExistWordsList2);
    }
    @Test
    @Order(2)
    @DisplayName("Should return non exist word with required id")
    public void getNonExistWordByIdTest() {
        NonExistWords nonExistWords = new NonExistWords();
        nonExistWords.setId(1L);
        nonExistWords.setWord("wieza");


        NonExistWords nonExistWords2 = new NonExistWords();
        nonExistWords2.setId(2L);
        nonExistWords2.setWord("dom");

        when(nonExistWordsRepository.findById(anyLong())).thenReturn(Optional.of(nonExistWords));

        Optional<NonExistWords> existingNonExistWord = nonExistWordsService.getNonExistWordsById(1L);

        assertNotNull(existingNonExistWord);
        assertThat(existingNonExistWord.get().getId()).isNotEqualTo(null);
    }
}