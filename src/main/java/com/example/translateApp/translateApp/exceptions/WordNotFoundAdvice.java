package com.example.translateApp.translateApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class WordNotFoundAdvice {

    @ExceptionHandler(WordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String wordNotFoundAdvice(WordNotFoundException ex){
        return ex.getMessage();
    }
}
