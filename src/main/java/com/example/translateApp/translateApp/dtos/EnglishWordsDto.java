package com.example.translateApp.translateApp.dtos;

import com.example.translateApp.translateApp.entities.PolishWords;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnglishWordsDto {

    private Long id;
    private String word;

    private PolishWords polishWords;

}
