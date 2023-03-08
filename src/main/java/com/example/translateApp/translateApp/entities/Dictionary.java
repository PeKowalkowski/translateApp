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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dictionary that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(words, that.words);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, words);
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "id=" + id +
                ", words=" + words +
                '}';
    }
}
