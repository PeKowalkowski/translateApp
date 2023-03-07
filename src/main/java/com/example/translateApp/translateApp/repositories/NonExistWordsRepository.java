package com.example.translateApp.translateApp.repositories;

import com.example.translateApp.translateApp.entities.NonExistWords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NonExistWordsRepository extends JpaRepository<NonExistWords, Long> {


}
