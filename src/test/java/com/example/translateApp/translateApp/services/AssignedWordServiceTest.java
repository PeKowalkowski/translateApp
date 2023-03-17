package com.example.translateApp.translateApp.services;

import com.example.translateApp.translateApp.entities.AssignedWord;
import com.example.translateApp.translateApp.entities.Words;
import com.example.translateApp.translateApp.enums.Language;
import com.example.translateApp.translateApp.mapper.WordsMapper;
import com.example.translateApp.translateApp.repositories.AssignedWordsRepository;
import com.example.translateApp.translateApp.repositories.NonExistWordsRepository;
/*
import com.example.translateApp.translateApp.repositories.WordsDtoRepository;
*/
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

@SpringBootTest(classes = {AssignedWordServiceTest.class})
class AssignedWordServiceTest {
    @Mock
    AssignedWordsRepository assignedWordsRepository;

    @InjectMocks
    AssignedWordService assignedWordService;
    public List<AssignedWord> assignedWordList;

    @Test
    @Order(1)
    @DisplayName("Should return list of assigned word with size 2")
    void getWordsTest() {
        AssignedWord assignedWord = new AssignedWord();
        assignedWord.setId(1L);
        assignedWord.setWord("tower");
        assignedWord.setLanguage(Language.ENGLISH);

        AssignedWord assignedWord2 = new AssignedWord();
        assignedWord2.setId(2L);
        assignedWord2.setWord("bird");
        assignedWord2.setLanguage(Language.ENGLISH);

        List<AssignedWord> assignedWords = new ArrayList<>();
        assignedWords.add(assignedWord);
        assignedWords.add(assignedWord2);

        when(assignedWordsRepository.findAll()).thenReturn(assignedWords);

        assignedWordList = assignedWordService.getAssignedWords();

        assertEquals(2, assignedWordList.size());
        assertNotNull(assignedWordList);
    }
    @Test
    @Order(2)
    @DisplayName("Should return assigned word with required id")
    public void getWordByIdTest() {
        AssignedWord assignedWord = new AssignedWord();
        assignedWord.setId(1L);
        assignedWord.setWord("tower");
        assignedWord.setLanguage(Language.ENGLISH);

        AssignedWord assignedWord2 = new AssignedWord();
        assignedWord2.setId(2L);
        assignedWord2.setWord("bird");
        assignedWord2.setLanguage(Language.ENGLISH);

        when(assignedWordsRepository.findById(anyLong())).thenReturn(Optional.of(assignedWord));

        Optional<AssignedWord> existingAssignedWord = assignedWordService.getAssignedWordById(1L);

        assertNotNull(existingAssignedWord);
        assertThat(existingAssignedWord.get().getId()).isNotEqualTo(null);
    }


}