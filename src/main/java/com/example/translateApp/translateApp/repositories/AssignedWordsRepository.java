package com.example.translateApp.translateApp.repositories;

import com.example.translateApp.translateApp.entities.AssignedWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignedWordsRepository extends JpaRepository<AssignedWord, Long> {
}
