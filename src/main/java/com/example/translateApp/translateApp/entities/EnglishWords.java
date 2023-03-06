package com.example.translateApp.translateApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Table(name = "englishWords")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnglishWords {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "word")
    private String word;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "polishWord", referencedColumnName = "id")
    private PolishWords polishWords;

    @OneToOne
    @JoinColumn(name = "dictionary_id")
    private Dictionary dictionary;

    public EnglishWords(Long id, String word) {
        this.id = id;
        this.word = word;
    }

    public EnglishWords(Long id, String word, PolishWords polishWords) {
        this.id = id;
        this.word = word;
        this.polishWords = polishWords;
    }
}
