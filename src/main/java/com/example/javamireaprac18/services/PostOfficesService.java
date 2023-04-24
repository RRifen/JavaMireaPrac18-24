package com.example.javamireaprac18.services;

import com.example.javamireaprac18.models.PostOffice;
import com.example.javamireaprac18.repositories.PostOfficesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@Slf4j
public class PostOfficesService {

    private final PostOfficesRepository postOfficesRepository;

    @Autowired
    public PostOfficesService(PostOfficesRepository postOfficesRepository) {
        this.postOfficesRepository = postOfficesRepository;
    }


    public List<PostOffice> findAll() {
        log.info("Find all Post Offices");

        return postOfficesRepository.findAll();
    }

    public List<PostOffice> findAllByName(String name) {
        log.info("Find all Post Offices with name like {}", name);

        name = "%" + name + "%";
        return postOfficesRepository.findByNameLike(name);
    }

    public List<PostOffice> findAllByCityName(String cityName) {
        log.info("Find all Post Offices with City Name like {}", cityName);

        cityName = "%" + cityName + "%";
        return postOfficesRepository.findByCityNameLike(cityName);
    }

    public PostOffice findOne(int id) {
        log.info("Find one Post Office with id {}", id);

        return postOfficesRepository.findById(id).orElse(null);
    }


    @Transactional
    public void save(PostOffice postOffice) {
        log.info("Save Post Office {}", postOffice);

        postOfficesRepository.save(postOffice);
    }

    @Transactional
    public void update(int id, PostOffice updatedPostOffice) {

        updatedPostOffice.setId(id);
        log.info("Update Post Office {}", updatedPostOffice);
        postOfficesRepository.save(updatedPostOffice);
    }

    @Transactional
    public void delete(int id) {
        log.info("Delete Post Office with id {}", id);

        postOfficesRepository.deleteById(id);
    }
}
