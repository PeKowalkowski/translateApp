package com.example.translateApp.translateApp.mapper;

import com.example.translateApp.translateApp.dtos.EnglishWordsDto;
import com.example.translateApp.translateApp.entities.EnglishWords;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EnglishWordsMapper {

    private ModelMapper modelMapper;

    public EnglishWordsMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EnglishWords englishWordsMapperDtoToEntity(EnglishWordsDto englishWordsDto){
        return new EnglishWords(englishWordsDto.getId(), englishWordsDto.getWord(), englishWordsDto.getPolishWords());
    }

    public EnglishWordsDto englishWordsMapperEntityToDto(EnglishWords englishWords){
        EnglishWordsDto englishWordsDto = modelMapper.map(englishWords, EnglishWordsDto.class);
        return englishWordsDto;
    }
}
