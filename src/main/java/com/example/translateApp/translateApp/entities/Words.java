package com.example.translateApp.translateApp.entities;

import com.example.translateApp.translateApp.enums.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "words")
@NoArgsConstructor
@AllArgsConstructor
public class Words {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String word;
    private String translation;

    @Enumerated(EnumType.STRING)
    private Language language;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "assignedWord", referencedColumnName = "id")
    private AssignedWord assignedWord;

    public Words(String word, String translation) {
        this.word = word;
        this.translation = translation;
    }

    public Words(Long id, String word) {
    }

    public Words(AssignedWord assignedWord) {
        this.assignedWord = assignedWord;
    }

    public Words(String word) {
        this.word = word;
    }


    public Words(String word, Language language) {
        this.word = word;
        this.language = language;
    }

    public Words(String word, AssignedWord assignedWord) {
        this.word = word;
        this.assignedWord = assignedWord;
    }

    public Words(Long id, String word, Language language) {
        this.id = id;
        this.word = word;
        this.language = language;
    }

    public Words(Long id, String word, Language language, AssignedWord assignedWord) {
        this.id = id;
        this.word = word;
        this.language = language;
        this.assignedWord = assignedWord;
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

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Words words)) return false;
        return Objects.equals(id, words.id) && Objects.equals(word, words.word) && language == words.language && Objects.equals(assignedWord, words.assignedWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word, language, assignedWord);
    }

    @Override
    public String toString() {
        return "Words{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", language=" + language +
                ", assignedWord=" + assignedWord +
                '}';
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Words words)) return false;
        return Objects.equals(id, words.id) && Objects.equals(word, words.word) && language == words.language && Objects.equals(assignedWord, words.assignedWord) && Objects.equals(translation, words.translation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word, language, assignedWord, translation);
    }

    @Override
    public String toString() {
        return "Words{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", language=" + language +
                ", assignedWord=" + assignedWord +
                ", translation='" + translation + '\'' +
                '}';
    }
}
