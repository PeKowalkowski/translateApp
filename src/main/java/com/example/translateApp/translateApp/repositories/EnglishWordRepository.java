package com.example.translateApp.translateApp.repositories;

import com.example.translateApp.translateApp.entities.Dictionary;
import com.example.translateApp.translateApp.entities.EnglishWords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface EnglishWordRepository extends JpaRepository<EnglishWords, Long> {

    @Query("select ew from EnglishWords ew where ew.word =:w")
    public List<EnglishWords> getByWord(@Param("w") String word);

   @Query("select ew from EnglishWords ew where LENGTH(word)=:l")
   public List<EnglishWords> getByLength(@Param("l") Long length);
    @Query("select count(word) from EnglishWords")
    Long getAmountOfEnglishWords();
    @Query(value = "select avg(length(word)) from EnglishWords")
    public Long getAvgLengthOfEnglishWords();

}
