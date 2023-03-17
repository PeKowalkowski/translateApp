package com.example.translateApp.translateApp.controllers;

import com.example.translateApp.translateApp.dtos.WordsDto;
import com.example.translateApp.translateApp.entities.AssignedWord;
import com.example.translateApp.translateApp.entities.Words;
import com.example.translateApp.translateApp.enums.Language;
import com.example.translateApp.translateApp.services.AssignedWordService;
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
@SpringBootTest(classes = {AssignedWordsControllerTest.class})
class AssignedWordsControllerTest {
    @Autowired
    MockMvc mockMvc;


    @Mock
    AssignedWordService assignedWordService;
    @InjectMocks
    AssignedWordsController assignedWordsController;
    List<AssignedWord> assignedWordList;
    AssignedWord assignedWord;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(assignedWordsController).build();
    }
    @Test
    @Order(1)
    @DisplayName("Should return list of assigned words with size 2")
    void getAssignedWordsTest() throws Exception{
        assignedWordList = new ArrayList<AssignedWord>();
        AssignedWord assignedWord1 = new AssignedWord();
        assignedWord1.setId(1L);
        assignedWord1.setWord("tower");
        assignedWord1.setLanguage(assignedWord1.getLanguage());

        AssignedWord assignedWord2 = new AssignedWord();
        assignedWord2.setId(2L);
        assignedWord2.setWord("bird");
        assignedWord2.setLanguage(assignedWord2.getLanguage());

        assignedWordList.add(assignedWord1);
        assignedWordList.add(assignedWord2);

        when(assignedWordService.getAssignedWords()).thenReturn(assignedWordList);

        this.mockMvc.perform(get("/api/asignedWordsController/getAssignedWords"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.size()", is(assignedWordList.size())))
                .andDo(print());

    }
    @Test
    @Order(2)
    @DisplayName("Should return assigned word with required id")
    public void getAssignedWordByIdControllerTest() throws Exception {
        assignedWord = new AssignedWord(1L, "bird", Language.ENGLISH);
        Long assignedWordId = 1L;

        when(assignedWordService.getAssignedWordById(assignedWordId)).thenReturn(Optional.ofNullable(assignedWord));

        this.mockMvc.perform(get("/api/asignedWordsController/getAssignedWordById/{id}", assignedWordId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.word").value("bird"))
                .andExpect(jsonPath("$.language").value("ENGLISH"))
                .andDo(print());
    }
}