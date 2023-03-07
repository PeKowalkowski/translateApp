package com.example.translateApp.translateApp.mapper;

import com.example.translateApp.translateApp.dtos.WordsDto;
import com.example.translateApp.translateApp.entities.Words;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WordsMapper {
    public WordsDto wordsToWordsDto(Words words);

    public Words wordsDtoToWords(WordsDto wordsDto);
}
