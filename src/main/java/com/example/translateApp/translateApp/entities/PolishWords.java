package com.example.translateApp.translateApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "polishWords")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolishWords {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "word")
    private String word;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "englishWord", referencedColumnName = "id")
    private EnglishWords englishWords;

    @OneToOne
    @JoinColumn(name = "dictionary_id")
    private Dictionary dictionary;


    public PolishWords(Long id, String word) {
        this.id = id;
        this.word = word;
    }

    public PolishWords(Long id, String word, EnglishWords englishWords) {
        this.id = id;
        this.word = word;
        this.englishWords = englishWords;
    }

    public PolishWords(EnglishWords englishWords) {
        this.englishWords = englishWords;
    }
}
