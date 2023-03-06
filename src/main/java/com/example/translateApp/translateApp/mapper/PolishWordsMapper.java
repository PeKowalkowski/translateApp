package com.example.translateApp.translateApp.mapper;

import com.example.translateApp.translateApp.dtos.EnglishWordsDto;
import com.example.translateApp.translateApp.dtos.PolishWordsDto;
import com.example.translateApp.translateApp.entities.EnglishWords;
import com.example.translateApp.translateApp.entities.PolishWords;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PolishWordsMapper {

    private ModelMapper modelMapper;

    public PolishWordsMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PolishWords polishWordsMapperDtoToEntity(PolishWordsDto polishWordsDto){
        return new PolishWords(polishWordsDto.getId(), polishWordsDto.getWord(), polishWordsDto.getEnglishWords());
    }

    public PolishWordsDto polishWordsMapperEntityToDto(PolishWords polishWords){
        PolishWordsDto polishWordsDto = modelMapper.map(polishWords, PolishWordsDto.class);
        return polishWordsDto;
    }
}
