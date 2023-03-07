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

    @Enumerated(EnumType.STRING)
    private Language language;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "assignedWord", referencedColumnName = "id")
    private AssignedWord assignedWord;
    @OneToOne
    @JoinColumn(name = "dictionary_id")
    private Dictionary dictionary;

    public Words(Long id, String word, Language language, AssignedWord assignedWord) {
        this.id = id;
        this.word = word;
        this.language = language;
        this.assignedWord = assignedWord;
    }

    public Words(Long id, String word) {
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

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Words words)) return false;
        return id.equals(words.id) && word.equals(words.word)
                && language == words.language && assignedWord.equals(words.assignedWord)
                && Objects.equals(dictionary, words.dictionary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, word, language, assignedWord, dictionary);
    }

    @Override
    public String toString() {
        return "Words{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", language=" + language +
                ", assignedWord=" + assignedWord +
                ", dictionary=" + dictionary +
                '}';
    }
}
