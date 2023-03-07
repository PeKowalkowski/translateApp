package com.example.translateApp.translateApp.repositories;

import com.example.translateApp.translateApp.entities.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {


}
