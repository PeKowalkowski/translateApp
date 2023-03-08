package com.example.translateApp.translateApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "non_exist_words")
@NoArgsConstructor
@AllArgsConstructor
public class NonExistWords {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String word;

    public NonExistWords(String word) {
        this.word = word;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NonExistWords that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(word, that.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word);
    }

    @Override
    public String toString() {
        return "NonExistWords{" +
                "id=" + id +
                ", word='" + word + '\'' +
                '}';
    }
}
