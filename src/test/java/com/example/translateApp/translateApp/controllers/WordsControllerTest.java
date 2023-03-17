package com.example.translateApp.translateApp.controllers;

import com.example.translateApp.translateApp.dtos.WordsDto;
import com.example.translateApp.translateApp.entities.AssignedWord;
import com.example.translateApp.translateApp.entities.Words;
import com.example.translateApp.translateApp.enums.Language;
import com.example.translateApp.translateApp.services.WordsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = {WordsControllerTest.class})
class WordsControllerTest {
    @Autowired
    MockMvc mockMvc;


    @Mock
    WordsService wordsService;
    @InjectMocks
    WordsController wordsController;
    List<Words> wordsList;
    Words words;
    WordsDto wordsDto;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(wordsController).build();
    }

   /* @Test
    @Order(1)
    @DisplayName("Should save new word with assigned word to database.")
    public void addWordWithAssignedWordTest() throws Exception {
        wordsDto = new WordsDto(1L, "trawa", Language.POLISH, new AssignedWord("grass", Language.ENGLISH));

        when(wordsService.addWordsWithAssignedWord(wordsDto)).thenReturn(wordsDto);

        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(wordsDto);

        this.mockMvc.perform(post("/api/words/addWordWithAssignedWord")
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }*/

    @Test
    @Order(2)
    @DisplayName("Should return list of words with size 2")
    public void getWordsControllerTest() throws Exception {
        wordsList = new ArrayList<Words>();
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

        wordsList.add(word);
        wordsList.add(word2);

        when(wordsService.getWords()).thenReturn(wordsList);

        this.mockMvc.perform(get("/api/words/getWords"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.size()", is(wordsList.size())))
                .andDo(print());


    }
    @Test
    @Order(3)
    @DisplayName("Should return word with required id")
    public void getWordByIdControllerTest() throws Exception {
        words = new Words(1L, "zaba", Language.POLISH, new AssignedWord("frog", Language.ENGLISH));
        Long wordId = 1L;

        when(wordsService.getWordById(wordId)).thenReturn(Optional.ofNullable(words));

        this.mockMvc.perform(get("/api/words/getWordById/{id}", wordId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.word").value("zaba"))
                .andExpect(jsonPath("$.language").value("POLISH"))
                .andExpect(jsonPath("$.assignedWord.word").value("frog"))
                .andExpect(jsonPath("$.assignedWord.language").value("ENGLISH"))
                .andDo(print());
    }

   @Test
    @Order(4)
    @DisplayName("Should transalte required word.")
    public void translateWordTest() throws Exception {
        words = new Words(1L, "zaba", "frog", Language.POLISH, new AssignedWord("frog", Language.ENGLISH));

       when(wordsService.getByWord(words.getWord())).thenReturn(words.getTranslation());

       this.mockMvc.perform(get("/api/words/getWordById/{id}", words.getWord()))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(1L))
               .andExpect(jsonPath("$.word").value("zaba"))
               .andExpect(jsonPath("$.translation").value("zaba"))
               .andExpect(jsonPath("$.language").value("POLISH"))
               .andExpect(jsonPath("$.assignedWord.word").value("frog"))
               .andExpect(jsonPath("$.assignedWord.language").value("ENGLISH"))
               .andDo(print());
    }

    @Test
    @Order(5)
    @DisplayName("Should return Polish word with length.")
    public void getPolishWordsWithLengthControllerTest() throws Exception {

        Words word = new Words();
        word.setId(1L);
        word.setWord("wieza");
        word.setLanguage(Language.POLISH);
        word.setAssignedWord(new AssignedWord("tower", Language.ENGLISH));

        Words word2 = new Words();
        word2.setId(2L);
        word2.setWord("bird");
        word2.setLanguage(Language.ENGLISH);
        word2.setAssignedWord(new AssignedWord("ptak", Language.POLISH));

        Long length = 5L;

        when(wordsService.getPolishWordsWithLength(length)).thenReturn(word2.getId());

        this.mockMvc.perform(get("/api/words/polishWord/{length}", length))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.word").value("wieza"))
                .andExpect(jsonPath("$.language").value("POLISH"))
                .andExpect(jsonPath("$.assignedWord.word").value("tower"))
                .andExpect(jsonPath("$.assignedWord.language").value("ENGLISH"))
                .andDo(print());

    }

    @Test
    @Order(7)
    @DisplayName("Should return amount of Polish words.")
    public void getAmountOfPolishWordsTest() throws Exception {

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

        when(wordsService.getAmountOfPolishWords()).thenReturn((long) list.size());

        this.mockMvc.perform(get("/api/words/polishWord/amountOfPolishWords/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.word").value("zaba"))
                .andExpect(jsonPath("$.language").value("POLISH"))
                .andExpect(jsonPath("$.assignedWord.word").value("frog"))
                .andExpect(jsonPath("$.assignedWord.language").value("ENGLISH"))
                .andDo(print());


    }
}