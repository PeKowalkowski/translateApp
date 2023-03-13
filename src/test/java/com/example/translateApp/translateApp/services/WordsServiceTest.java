package com.example.translateApp.translateApp.services;

import com.example.translateApp.translateApp.dtos.WordsDto;
import com.example.translateApp.translateApp.entities.AssignedWord;
import com.example.translateApp.translateApp.entities.Words;
import com.example.translateApp.translateApp.enums.Language;
import com.example.translateApp.translateApp.mapper.WordsMapper;
import com.example.translateApp.translateApp.repositories.NonExistWordsRepository;
import com.example.translateApp.translateApp.repositories.WordsDtoRepository;
import com.example.translateApp.translateApp.repositories.WordsRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void getWordsTest() {
        List<Words> words = new ArrayList<Words>();
        words.add(new Words(1L, "drzewo", Language.POLISH, new AssignedWord("tree", Language.ENGLISH)));
        words.add(new Words(2L, "szklo", Language.POLISH, new AssignedWord("glass", Language.ENGLISH)));
        when(wordsRepository.findAll()).thenReturn(words);

        assertEquals(2, wordsService.getWords().size());
    }

    @Test
    @Order(2)
    public void getWordByIdTest() {
        List<Words> words = new ArrayList<Words>();
        words.add(new Words(1L, "sciana", Language.POLISH, new AssignedWord("wall", Language.ENGLISH)));
        words.add(new Words(2L, "podloga", Language.POLISH, new AssignedWord("floor", Language.ENGLISH)));
        Long wordId = 1L;

        when(wordsRepository.findAll()).thenReturn(words);

        assertEquals(wordId, wordsService.getWordById(wordId));

    }

    @Test
    @Order(3)
    public void translateWordTest() {
        List<Words> words = new ArrayList<Words>();
        words.add(new Words(1L, "sciana", Language.POLISH, new AssignedWord("wall", Language.ENGLISH)));
        words.add(new Words(2L, "podloga", Language.POLISH, new AssignedWord("floor", Language.ENGLISH)));
        String wordToTranslate = "podloga";

        when(wordsRepository.findAll()).thenReturn(words);

        assertEquals(wordToTranslate, wordsService.getByWord(wordToTranslate));

    }

    @Test
    @Order(4)
    public void addWordsWithAssignedWordTest() {

        WordsDto word = new WordsDto(3L, "wieza", Language.POLISH, new AssignedWord("tower", Language.ENGLISH));
        when(wordsDtoRepository.save(word)).thenReturn(word);
        assertEquals(word, wordsService.addWordsWithAssignedWord(word));

    }
}