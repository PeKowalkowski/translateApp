package com.example.translateApp.translateApp.services;

import com.example.translateApp.translateApp.dtos.WordsDto;
import com.example.translateApp.translateApp.entities.AssignedWord;
import com.example.translateApp.translateApp.entities.Words;
import com.example.translateApp.translateApp.enums.Language;
import com.example.translateApp.translateApp.exceptions.WordNotFoundException;
import com.example.translateApp.translateApp.mapper.WordsMapper;
import com.example.translateApp.translateApp.repositories.NonExistWordsRepository;
import com.example.translateApp.translateApp.repositories.WordsDtoRepository;
import com.example.translateApp.translateApp.repositories.WordsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {WordsServiceTest.class})

class WordsServiceTest {

    @Mock
    WordsRepository wordsRepository;
    @Mock
    WordsDtoRepository wordsDtoRepository;
    @Mock
    NonExistWordsRepository nonExistWordsRepository;
    @InjectMocks
    WordsService wordsService;
    public List<Words> wordsList;

    @Mock
    WordsMapper wordsMapper;
    @Test
    @Order(1)
    @DisplayName("Should save new word with assigned word to database.")
    public void addWordsWithAssignedWordTest() {

        WordsDto word = new WordsDto();/*(3L, "wieza", Language.POLISH, new AssignedWord("tower", Language.ENGLISH))*/;
        word.setId(1L);
        word.setWord("wieza");
        word.setLanguage(Language.POLISH);
        word.setAssignedWord(new AssignedWord("tower", Language.ENGLISH));
        when(wordsDtoRepository.save(word)).thenReturn(word);

        WordsDto newWordsDto = wordsService.addWordsWithAssignedWord(word);

        assertEquals(newWordsDto, wordsService.addWordsWithAssignedWord(word));

        assertNotNull(newWordsDto);
        assertThat(newWordsDto.getId()).isEqualTo(1L);
        assertThat(newWordsDto.getWord()).isEqualTo("wieza");
        assertThat(newWordsDto.getLanguage()).isEqualTo(Language.POLISH);
        assertThat(newWordsDto.getAssignedWord()).isEqualTo(new AssignedWord("tower", Language.ENGLISH));
    }


    @Test
    @Order(2)
    @DisplayName("Should return list of words with size 2")
    void getWordsTest() {
        Words word = new Words();
        word.setId(1L);
        word.setWord("wieza");
        word.setLanguage(Language.POLISH);
        word.setAssignedWord(new AssignedWord("tower", Language.ENGLISH));

        Words word2 = new Words();
        word2.setId(2L);
        word2.setWord("ptak");
        word2.setLanguage(Language.POLISH);
        word2.setAssignedWord(new AssignedWord("bird", Language.ENGLISH));

        List<Words> list = new ArrayList<>();
        list.add(word);
        list.add(word2);

        when(wordsRepository.findAll()).thenReturn(list);

        List<Words> words = wordsService.getWords();

        assertEquals(2, words.size());
        assertNotNull(words);
    }
   @Test
   @Order(3)
   @DisplayName("Should return word with required id")
   public void getWordByIdTest() {
       Words word = new Words();
       word.setId(1L);
       word.setWord("wieza");
       word.setLanguage(Language.POLISH);
       word.setAssignedWord(new AssignedWord("tower", Language.ENGLISH));

       Words word2 = new Words();
       word2.setId(2L);
       word2.setWord("ptak");
       word2.setLanguage(Language.POLISH);
       word2.setAssignedWord(new AssignedWord("bird", Language.ENGLISH));

       when(wordsRepository.findById(anyLong())).thenReturn(Optional.of(word));

       Optional<Words> existingWord = wordsService.getWordById(1L);

       assertNotNull(existingWord);
       assertThat(existingWord.get().getId()).isNotEqualTo(null);
   }
    @Test
    @Order(4)
    @DisplayName("Should throw Exception.")
    public void getWordByIdTestForException() {
        Words word = new Words();
        word.setId(1L);
        word.setWord("wieza");
        word.setLanguage(Language.POLISH);
        word.setAssignedWord(new AssignedWord("tower", Language.ENGLISH));

        Words word2 = new Words();
        word2.setId(2L);
        word2.setWord("ptak");
        word2.setLanguage(Language.POLISH);
        word2.setAssignedWord(new AssignedWord("bird", Language.ENGLISH));

        when(wordsRepository.findById(1L)).thenReturn(Optional.of(word));

        assertThrows(WordNotFoundException.class, () ->{
            wordsService.getWordById(3L);
        });

    }

    @Test
    @Order(5)
    public void translateWordTest() {
        Words word = new Words();
        word.setId(1L);
        word.setWord("wieza");
        word.setLanguage(Language.POLISH);
        word.setAssignedWord(new AssignedWord("tower", Language.ENGLISH));

        Words word2 = new Words();
        word2.setId(2L);
        word2.setWord("ptak");
        word2.setLanguage(Language.POLISH);
        word2.setAssignedWord(new AssignedWord("bird", Language.ENGLISH));

        String wordToTranslate = "wieza";


        assertEquals(wordToTranslate, wordsService.getByWord(word.getAssignedWord().getWord()));

    }



}