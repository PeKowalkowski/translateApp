package com.example.translateApp.translateApp.mapper;

import com.example.translateApp.translateApp.dtos.WordsDto;
import com.example.translateApp.translateApp.entities.Words;
import org.springframework.stereotype.Component;

@Component
public class WordsMapperImpl implements WordsMapper{
    @Override
    public WordsDto wordsToWordsDto(Words words) {
        WordsDto wordsDto = new WordsDto();

        wordsDto.setWord(words.getWord());
        wordsDto.setLanguage(words.getLanguage());
        wordsDto.setAssignedWord(words.getAssignedWord());
        wordsDto.setTranslation(words.getTranslation());
        return wordsDto;
    }

    @Override
    public Words wordsDtoToWords(WordsDto wordsDto) {
        Words words = new Words();

        words.setWord(wordsDto.getWord());
        words.setLanguage(wordsDto.getLanguage());
        words.setAssignedWord(wordsDto.getAssignedWord());
        words.setTranslation(wordsDto.getTranslation());
        return words;
    }
}
