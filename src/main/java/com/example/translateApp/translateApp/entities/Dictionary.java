package com.example.translateApp.translateApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "dictionary")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private PolishWords polishWords;

    @OneToOne(cascade = CascadeType.ALL)
    private EnglishWords englishWords;




    @OneToOne(cascade = CascadeType.ALL)
    private NonExistWords nonExistWords;

    public Dictionary(PolishWords polishWords) {
        this.polishWords = polishWords;
    }

    public Dictionary(Long id, PolishWords polishWords, EnglishWords englishWords) {
        this.id = id;
        this.polishWords = polishWords;
        this.englishWords = englishWords;
    }

    public Dictionary(EnglishWords englishWords) {
        this.englishWords = englishWords;
    }




}
