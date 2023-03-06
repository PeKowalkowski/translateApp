package com.example.translateApp.translateApp.repositories;

import com.example.translateApp.translateApp.entities.Dictionary;
import com.example.translateApp.translateApp.entities.EnglishWords;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {

    @Query("select d from Dictionary d where d.englishWords.id =:wId")
    public List<Dictionary> getByEnglishWord(@Param("wId") Long word);

   @Query("select d from Dictionary d where d.polishWords.id =:wId")
    public List<Dictionary> getByPolishWord(@Param("wId") Long word);

   /*public Page<Dictionary> getWithPageNumberAndSize(Pageable pageable);*/
}
