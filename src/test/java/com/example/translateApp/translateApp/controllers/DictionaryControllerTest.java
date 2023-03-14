package com.example.translateApp.translateApp.controllers;

import com.example.translateApp.translateApp.dtos.DictionaryDto;
import com.example.translateApp.translateApp.dtos.WordsDto;
import com.example.translateApp.translateApp.entities.AssignedWord;
import com.example.translateApp.translateApp.entities.Dictionary;
import com.example.translateApp.translateApp.entities.Words;
import com.example.translateApp.translateApp.enums.Language;
import com.example.translateApp.translateApp.services.DictionaryService;
import com.example.translateApp.translateApp.services.WordsService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = {DictionaryControllerTest.class})
class DictionaryControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Mock
    DictionaryService dictionaryService;
    @InjectMocks
    DictionaryController dictionaryController;
    List<Dictionary> dictionaryList;
    Dictionary dictionary;

    DictionaryDto dictionaryDto;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(dictionaryController).build();
    }

    @Test
    @Order(1)
    @DisplayName("Should return list of dictionary with size 2")
    public void getDictionariesControllerTest() throws Exception {
        dictionaryList = new ArrayList<Dictionary>();
        Dictionary dictionaries = new Dictionary();
        dictionaries.setId(1L);
        dictionaries.setWords(new Words(1L, "wieza", Language.POLISH, new AssignedWord("tower", Language.ENGLISH)));

        Dictionary dictionaries2 = new Dictionary();
        dictionaries2.setId(2L);
        dictionaries2.setWords(new Words(2L, "zaba", Language.POLISH, new AssignedWord("frog", Language.ENGLISH)));

        dictionaryList.add(dictionaries);
        dictionaryList.add(dictionaries2);

        when(dictionaryService.getDictionaries()).thenReturn(dictionaryList);

        this.mockMvc.perform(get("/api/dictionary/getDictionaries"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.size()", is(dictionaryList.size())))
                .andDo(print());
    }

    @Test
    @Order(2)
    @DisplayName("Should return dictionary with required id")
    public void getDictionaryByIdControllerTest() throws Exception {

        Dictionary dictionaries = new Dictionary();
        dictionaries.setId(1L);
        dictionaries.setWords(new Words(1L, "wieza", Language.POLISH, new AssignedWord("tower", Language.ENGLISH)));

        Dictionary dictionaries2 = new Dictionary();
        dictionaries2.setId(2L);
        dictionaries2.setWords(new Words(2L, "zaba", Language.POLISH, new AssignedWord("frog", Language.ENGLISH)));

        Long dictionaryId = 1L;

        when(dictionaryService.getDictionariesById(dictionaryId)).thenReturn(Optional.ofNullable(dictionaries));

        this.mockMvc.perform(get("/api/dictionary/getDictionaries/{id}", dictionaryId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.words.id").value(1L))
                .andExpect(jsonPath("$.words.word").value("wieza"))
                .andExpect(jsonPath("$.words.language").value("POLISH"))
                .andExpect(jsonPath("$.words.assignedWord.word").value("tower"))
/*
                .andExpect(jsonPath("$.words.assignedWord.language").value("ENGLISH"))
*/
                .andDo(print());
    }

}