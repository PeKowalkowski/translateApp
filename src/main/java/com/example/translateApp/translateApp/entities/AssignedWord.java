package com.example.translateApp.translateApp.entities;

import com.example.translateApp.translateApp.enums.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "asigned_word")
@NoArgsConstructor
@AllArgsConstructor
public class AssignedWord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String word;

    private Language language;


    public AssignedWord(String word) {
        this.word = word;
    }

    public AssignedWord(String word, Language language) {
        this.word = word;
        this.language = language;
    }


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AssignedWord that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(word, that.word) && language == that.language;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word, language);
    }

    @Override
    public String toString() {
        return "AssignedWord{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", language=" + language +
                '}';
    }
}
