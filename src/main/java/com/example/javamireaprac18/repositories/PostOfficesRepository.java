package com.example.javamireaprac18.repositories;

import com.example.javamireaprac18.models.PostOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostOfficesRepository extends JpaRepository<PostOffice, Integer> {
    List<PostOffice> findByNameLike(String name);

    List<PostOffice> findByCityNameLike(String cityName);
}
