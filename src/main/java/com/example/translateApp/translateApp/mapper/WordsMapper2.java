package com.example.translateApp.translateApp.mapper;

import com.example.translateApp.translateApp.dtos.WordsDto;
import com.example.translateApp.translateApp.entities.Words;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WordsMapper2 {

    @Mapping(target = "id", source = "words.id")
    @Mapping(target = "word", source = "words.word")
    @Mapping(target = "language", source = "words.language")
    @Mapping(target = "assignedWordsDto", source = "words.assignedWord")
    public WordsDto wordsToWordsDto(Words words);

    @Mapping(target = "id", source = "wordsDto.id")
    @Mapping(target = "word", source = "wordsDto.word")
    @Mapping(target = "language", source = "wordsDto.language")
    @Mapping(target = "assignedWord", source = "wordsDto.assignedWordsDto")
    public Words wordsDtoToWords(WordsDto wordsDto);


}
