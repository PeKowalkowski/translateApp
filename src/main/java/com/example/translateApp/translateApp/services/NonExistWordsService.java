package com.example.translateApp.translateApp.services;

import com.example.translateApp.translateApp.entities.EnglishWords;
import com.example.translateApp.translateApp.entities.NonExistWords;
import com.example.translateApp.translateApp.repositories.NonExistWordsRepository;
import org.springframework.stereotype.Service;

@Service
public class NonExistWordsService {

    private NonExistWordsRepository nonExistWordsRepository;

    public NonExistWordsService(NonExistWordsRepository nonExistWordsRepository) {
        this.nonExistWordsRepository = nonExistWordsRepository;
    }


//    public NonExistWords save(Long englishWordId) {
//        nonExistWordsRepository.save(englishWordId)
//    }
}
