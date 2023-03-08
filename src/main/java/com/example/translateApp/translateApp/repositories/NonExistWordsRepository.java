package com.example.translateApp.translateApp.repositories;

import com.example.translateApp.translateApp.entities.NonExistWords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NonExistWordsRepository extends JpaRepository<NonExistWords, Long> {

    @Query("select n from NonExistWords n where n.word =:w")
    public List<NonExistWords> getByWord(@Param("w") String word);

    @Query(value = "select count(word) from NonExistWords n ")
    public Long getAmountOfNonExistWords();
}
