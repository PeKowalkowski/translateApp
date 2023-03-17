package com.example.translateApp.translateApp.dtos;

import com.example.translateApp.translateApp.entities.AssignedWord;
import com.example.translateApp.translateApp.entities.Dictionary;
import com.example.translateApp.translateApp.enums.Language;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor

public class WordsDto {
    private Long id;
    private String word;
    private String translation;
    private Language language;
    private AssignedWord assignedWord;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public AssignedWord getAssignedWord() {
        return assignedWord;
    }

    public void setAssignedWord(AssignedWord assignedWord) {
        this.assignedWord = assignedWord;
    }


    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WordsDto wordsDto)) return false;
        return Objects.equals(id, wordsDto.id) && Objects.equals(word, wordsDto.word) && language == wordsDto.language && Objects.equals(assignedWord, wordsDto.assignedWord)
                && Objects.equals(translation, wordsDto.translation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word, language, assignedWord, translation);
    }

    @Override
    public String toString() {
        return "WordsDto{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", language=" + language +
                ", assignedWord=" + assignedWord +
                ", translation='" + translation + '\'' +
                '}';
    }
    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WordsDto wordsDto)) return false;
        return Objects.equals(id, wordsDto.id) && Objects.equals(word, wordsDto.word) && language == wordsDto.language && Objects.equals(assignedWord, wordsDto.assignedWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word, language, assignedWord);
    }

    @Override
    public String toString() {
        return "WordsDto{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", language=" + language +
                ", assignedWord=" + assignedWord +
                '}';
    }*/
}
