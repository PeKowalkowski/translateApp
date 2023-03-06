package com.example.translateApp.translateApp.dtos;

import com.example.translateApp.translateApp.entities.EnglishWords;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolishWordsDto {
    private Long id;
    private String word;
    private EnglishWords englishWords;
}
