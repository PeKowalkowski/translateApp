package com.example.translateApp.translateApp.services;

import com.example.translateApp.translateApp.repositories.NonExistWordsRepository;
import org.springframework.stereotype.Service;

@Service
public class NonExistWordsService {

    private NonExistWordsRepository nonExistWordsRepository;

    public NonExistWordsService(NonExistWordsRepository nonExistWordsRepository) {
        this.nonExistWordsRepository = nonExistWordsRepository;
    }

}
