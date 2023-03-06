package com.example.translateApp.translateApp.repositories;

import com.example.translateApp.translateApp.entities.EnglishWords;
import com.example.translateApp.translateApp.entities.PolishWords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface PolishWordRepository extends JpaRepository<PolishWords, Long> {
    @Query("select pw from PolishWords pw where pw.word =:w")
    public List<PolishWords> getByWord(@Param("w") String word);
    @Query("select pw from PolishWords pw where LENGTH(word)=:l")
    public List<PolishWords> getByLength(@Param("l") Long length);
    @Query("select count(word) from PolishWords")
    public Long getAmountOfPolishWords();

    @Query(value = "select avg(length(word)) from PolishWords")
    public Long getAvgLengthOfPolishWords();
}
