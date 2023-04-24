package com.example.javamireaprac18.services;

import com.example.javamireaprac18.models.Departure;
import com.example.javamireaprac18.repositories.DeparturesRepository;
import com.example.javamireaprac18.repositories.PeopleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
@Transactional(readOnly = true)
@Slf4j
public class DeparturesService {


    private final DeparturesRepository departuresRepository;
    private final PeopleRepository peopleRepository;

    @Autowired
    public DeparturesService(DeparturesRepository departuresRepository, PeopleRepository peopleRepository) {
        this.departuresRepository = departuresRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Departure> finaAll() {
        log.info("Find all departures");
        System.out.println(peopleRepository.findByUsername("test_user"));
        return departuresRepository.findAll();
    }

    public List<Departure> findAllByType(String type) {
        log.info("Find all Departures with type like {}", type);
        type = "%" + type + "%";
        return departuresRepository.findByTypeLike(type);
    }

    public List<Departure> findAllByDate(String dateInString) {
        log.info("Formatting user date {}", dateInString);

        //Date formatting
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date date;
        try {
            date = formatter.parse(dateInString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        log.info("Find all Departures with departure date greater or equal than {}", date);

        return departuresRepository.findByDepartureDateGreaterThanEqual(date);
    }


    public Departure findOne(int id) {
        log.info("Find one Departure by id {}", id);

        return departuresRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Departure departure) {
        log.info("Save Departure {}", departure);

        departuresRepository.save(departure);
    }

    @Transactional
    public void update(int id, Departure updatedDeparture) {

        updatedDeparture.setId(id);
        log.info("Update departure {}", updatedDeparture);
        departuresRepository.save(updatedDeparture);
    }

    @Transactional
    public void delete(int id) {
        log.info("Delete Departure with id {}", id);

        departuresRepository.deleteById(id);
    }
}
