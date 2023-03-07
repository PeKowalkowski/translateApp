package com.example.translateApp.translateApp.dtos;

import com.example.translateApp.translateApp.entities.AssignedWord;
import com.example.translateApp.translateApp.entities.Words;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class DictionaryDto {

    private Long id;

    private Words words;

    private AssignedWord assignedWord;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Words getWords() {
        return words;
    }

    public void setWords(Words words) {
        this.words = words;
    }

    public AssignedWord getAssignedWord() {
        return assignedWord;
    }

    public void setAssignedWord(AssignedWord assignedWord) {
        this.assignedWord = assignedWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DictionaryDto that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(words, that.words)
                && Objects.equals(assignedWord, that.assignedWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, words, assignedWord);
    }

    @Override
    public String toString() {
        return "DictionaryDto{" +
                "id=" + id +
                ", words=" + words +
                ", assignedWord=" + assignedWord +
                '}';
    }
}
