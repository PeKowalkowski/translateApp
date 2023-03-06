package com.example.translateApp.translateApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "non_exist_words")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NonExistWords {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String word;
}
