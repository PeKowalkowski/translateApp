package com.example.translateApp.translateApp.mapper;

import com.example.translateApp.translateApp.dtos.WordsDto;
import com.example.translateApp.translateApp.entities.Words;
import org.springframework.stereotype.Component;

@Component
public class WordsMapperImpl implements WordsMapper{
    @Override
    public WordsDto wordsToWordsDto(Words words) {
        WordsDto wordsDto = new WordsDto();

        wordsDto.setId(words.getId());
        wordsDto.setWord(words.getWord());
        wordsDto.setLanguage(words.getLanguage());
        wordsDto.setAssignedWord(words.getAssignedWord());
        wordsDto.setDictionary(words.getDictionary());
        return wordsDto;
    }

    @Override
    public Words wordsDtoToWords(WordsDto wordsDto) {
        Words words = new Words();

        words.setId(wordsDto.getId());
        words.setWord(wordsDto.getWord());
        words.setLanguage(wordsDto.getLanguage());
        words.setAssignedWord(wordsDto.getAssignedWord());
        words.setDictionary(wordsDto.getDictionary());

        return words;
    }
}
