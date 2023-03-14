package com.example.translateApp.translateApp.repositories;

import com.example.translateApp.translateApp.entities.Words;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface WordsRepository extends JpaRepository<Words, Long> {

    @Query("select wrd from Words wrd where wrd.word =:w")
    public List<Words> getByWord(@Param("w") String word);

    @Query("select count(word) from Words wrd where LENGTH(word)=:l and wrd.language = 'POLISH'")
    public Long getPolishWordsByLength(@Param("l") Long length);

    @Query("select count(word) from Words wrd where LENGTH(word)=:l and wrd.language = 'ENGLISH'")
    public Long getEnglishWordsByLength(@Param("l") Long length);

    @Query(value = "select count(word) from Words wrd where wrd.language = 'POLISH'")
    public Long getAmountOfPolishWords();

    @Query(value = "select count(word) from Words wrd where wrd.language = 'ENGLISH'")
    public Long getAmountOfEnglishWords();

    @Query(value = "select avg(length(word)) from Words where language = 'POLISH'")
    public Long getAvgLengthOfPolishWords();

    @Query(value = "select avg(length(word)) from Words where language = 'ENGLISH'")
    public Long getAvgLengthOfEnglishWords();

}
