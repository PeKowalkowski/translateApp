package com.example.translateApp.translateApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "raport")
@NoArgsConstructor
@AllArgsConstructor
public class Raport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long amountOfPolishWords;
    private Long averageLengthOfPolishWords;
    private Long polishWordsWithLength;
    private Long amountOfEnglishWords;
    private Long averageLengthOfEnglishWords;
    private Long englishWordsWithLength;
    private Long amountOfNonExistWords;

    public Raport(Long amountOfPolishWords, Long averageLengthOfPolishWords, Long polishWordsWithLength, Long amountOfEnglishWords,
                  Long averageLengthOfEnglishWords, Long englishWordsWithLength, Long amountOfNonExistWords) {
        this.amountOfPolishWords = amountOfPolishWords;
        this.averageLengthOfPolishWords = averageLengthOfPolishWords;
        this.polishWordsWithLength = polishWordsWithLength;
        this.amountOfEnglishWords = amountOfEnglishWords;
        this.averageLengthOfEnglishWords = averageLengthOfEnglishWords;
        this.englishWordsWithLength = englishWordsWithLength;
        this.amountOfNonExistWords = amountOfNonExistWords;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmountOfPolishWords() {
        return amountOfPolishWords;
    }

    public void setAmountOfPolishWords(Long amountOfPolishWords) {
        this.amountOfPolishWords = amountOfPolishWords;
    }

    public Long getAverageLengthOfPolishWords() {
        return averageLengthOfPolishWords;
    }

    public void setAverageLengthOfPolishWords(Long averageLengthOfPolishWords) {
        this.averageLengthOfPolishWords = averageLengthOfPolishWords;
    }

    public Long getPolishWordsWithLength() {
        return polishWordsWithLength;
    }

    public void setPolishWordsWithLength(Long polishWordsWithLength) {
        this.polishWordsWithLength = polishWordsWithLength;
    }

    public Long getAmountOfEnglishWords() {
        return amountOfEnglishWords;
    }

    public void setAmountOfEnglishWords(Long amountOfEnglishWords) {
        this.amountOfEnglishWords = amountOfEnglishWords;
    }

    public Long getAverageLengthOfEnglishWords() {
        return averageLengthOfEnglishWords;
    }

    public void setAverageLengthOfEnglishWords(Long averageLengthOfEnglishWords) {
        this.averageLengthOfEnglishWords = averageLengthOfEnglishWords;
    }

    public Long getEnglishWordsWithLength() {
        return englishWordsWithLength;
    }

    public void setEnglishWordsWithLength(Long englishWordsWithLength) {
        this.englishWordsWithLength = englishWordsWithLength;
    }

    public Long getAmountOfNonExistWords() {
        return amountOfNonExistWords;
    }

    public void setAmountOfNonExistWords(Long amountOfNonExistWords) {
        this.amountOfNonExistWords = amountOfNonExistWords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Raport raport)) return false;
        return Objects.equals(id, raport.id) && Objects.equals(amountOfPolishWords, raport.amountOfPolishWords)
                && Objects.equals(averageLengthOfPolishWords, raport.averageLengthOfPolishWords) && Objects.equals(polishWordsWithLength, raport.polishWordsWithLength)
                && Objects.equals(amountOfEnglishWords, raport.amountOfEnglishWords) && Objects.equals(averageLengthOfEnglishWords, raport.averageLengthOfEnglishWords)
                && Objects.equals(englishWordsWithLength, raport.englishWordsWithLength) && Objects.equals(amountOfNonExistWords, raport.amountOfNonExistWords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amountOfPolishWords, averageLengthOfPolishWords, polishWordsWithLength, amountOfEnglishWords, averageLengthOfEnglishWords,
                englishWordsWithLength, amountOfNonExistWords);
    }

    @Override
    public String toString() {
        return "Raport{" +
                "id=" + id +
                ", amountOfPolishWords=" + amountOfPolishWords +
                ", averageLengthOfPolishWords=" + averageLengthOfPolishWords +
                ", polishWordsWithLength=" + polishWordsWithLength +
                ", amountOfEnglishWords=" + amountOfEnglishWords +
                ", averageLengthOfEnglishWords=" + averageLengthOfEnglishWords +
                ", englishWordsWithLength=" + englishWordsWithLength +
                ", amountOfNonExistWords=" + amountOfNonExistWords +
                '}';
    }
}
