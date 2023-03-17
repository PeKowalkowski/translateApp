package com.example.translateApp.translateApp.controllers;

import com.example.translateApp.translateApp.dtos.WordsDto;
import com.example.translateApp.translateApp.entities.AssignedWord;
import com.example.translateApp.translateApp.entities.NonExistWords;
import com.example.translateApp.translateApp.entities.Words;
import com.example.translateApp.translateApp.enums.Language;
import com.example.translateApp.translateApp.services.NonExistWordsService;
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
@SpringBootTest(classes = {NonExistWordsControllerTest.class})
class NonExistWordsControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Mock
    NonExistWordsService nonExistWordsService;
    @InjectMocks
    NonExistWordsController nonExistWordsController;
    List<NonExistWords> nonExistWordsList;
    NonExistWords nonExistWords;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(nonExistWordsController).build();
    }

    @Test
    @Order(1)
    @DisplayName("Should return list of non exist words with size 2")
    public void getNonExistWordsControllerTest() throws Exception {
        nonExistWordsList = new ArrayList<NonExistWords>();
        NonExistWords nonExistWords1 = new NonExistWords();
        nonExistWords1.setId(1L);
        nonExistWords1.setWord("dom");

        NonExistWords nonExistWords2 = new NonExistWords();
        nonExistWords2.setId(2L);
        nonExistWords2.setWord("car");

        nonExistWordsList.add(nonExistWords1);
        nonExistWordsList.add(nonExistWords2);

        when(nonExistWordsService.getNonExistWords()).thenReturn(nonExistWordsList);

        this.mockMvc.perform(get("/api/nonExistWords/getNonExistWords"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.size()", is(nonExistWordsList.size())))
                .andDo(print());
    }

    @Test
    @Order(2)
    @DisplayName("Should return non exist word with required id")
    public void getNonExistWordByIdControllerTest() throws Exception {
        nonExistWords = new NonExistWords(1L, "brat");
        Long nonExistWordId = 1L;

        when(nonExistWordsService.getNonExistWordsById(nonExistWordId)).thenReturn(Optional.ofNullable(nonExistWords));
        this.mockMvc.perform(get("/api/nonExistWords/getNonExistWordsById/{id}", nonExistWordId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.word").value("brat"))
/*
                .andExpect(jsonPath("$.assignedWord.language").value("ENGLISH"))
*/
                .andDo(print());
    }

}