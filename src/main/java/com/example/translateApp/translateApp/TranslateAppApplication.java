package com.example.translateApp.translateApp;

import com.example.translateApp.translateApp.dtos.WordsDto;
import com.example.translateApp.translateApp.entities.Words;
import com.example.translateApp.translateApp.mapper.WordsMapper2;
import com.example.translateApp.translateApp.mapper.WordsMapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TranslateAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TranslateAppApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	@Bean
	public WordsMapper2 wordsMapper(){
		return new WordsMapper2() {
			@Override
			public WordsDto wordsToWordsDto(Words words) {
				return null;
			}

			@Override
			public Words wordsDtoToWords(WordsDto wordsDto) {
				return null;
			}
		};
	}

	private WordsMapper wordsMapper;

}
