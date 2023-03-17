package com.example.translateApp.translateApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "dictionary")
@NoArgsConstructor
@AllArgsConstructor
public class Dictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "words_id")
    private Words words;
    private String word;

    private String translate;

    public Dictionary(Long id, Words words) {
        this.id = id;
        this.words = words;
    }

    public Dictionary(Long id, String word, String translate) {
        this.id = id;
        this.word = word;
        this.translate = translate;
    }

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

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dictionary that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(words, that.words) && Objects.equals(word, that.word) && Objects.equals(translate, that.translate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, words, word, translate);
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "id=" + id +
                ", words=" + words +
                ", word='" + word + '\'' +
                ", translate='" + translate + '\'' +
                '}';
    }
}
