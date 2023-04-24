package com.example.javamireaprac18.repositories;

import com.example.javamireaprac18.models.Departure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface DeparturesRepository extends JpaRepository<Departure, Integer> {
    List<Departure> findByTypeLike(String type);
    List<Departure> findByDepartureDateGreaterThanEqual(Date date);
}
