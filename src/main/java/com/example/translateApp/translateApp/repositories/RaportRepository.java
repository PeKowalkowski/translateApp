package com.example.translateApp.translateApp.repositories;

import com.example.translateApp.translateApp.entities.Raport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaportRepository extends JpaRepository<Raport, Long> {
}
