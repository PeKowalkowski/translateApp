package com.example.translateApp.translateApp.dtos;

import com.example.translateApp.translateApp.entities.Dictionary;
import com.example.translateApp.translateApp.entities.Words;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor

public class AssignedWordsDto {

    private Long id;
    private String word;

    private Words words;

    private Dictionary dictionary;

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

    public Words getWords() {
        return words;
    }

    public void setWords(Words words) {
        this.words = words;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AssignedWordsDto that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(word, that.word)
                && Objects.equals(words, that.words)
                && Objects.equals(dictionary, that.dictionary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word, words, dictionary);
    }

    @Override
    public String toString() {
        return "AssignedWordsDto{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", words=" + words +
                ", dictionary=" + dictionary +
                '}';
    }
}
