package com.example.translateApp.translateApp.exceptions;

public class WordNotFoundException extends RuntimeException{
   public WordNotFoundException(Long id){
       super("Word with id " + id + ", dont exist.");
   }

   public WordNotFoundException(String word){
       super("Word : " + word + " dont exist.");
   }


}
