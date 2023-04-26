package com.example.javamireaprac18.services;

import com.example.javamireaprac18.models.Departure;
import com.example.javamireaprac18.repositories.DeparturesRepository;
import com.example.javamireaprac18.repositories.PeopleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DeparturesServiceTest {

    @Mock
    private DeparturesRepository departuresRepository;

    @Mock
    private PeopleRepository peopleRepository;

    @Captor
    ArgumentCaptor<Integer> captor;

    @Test
    void finaAll() {
        Departure departure1 = new Departure("Type1", new Date());
        Departure departure2 = new Departure("Type2", new Date());

        Mockito.when(departuresRepository.findAll()).thenReturn(List.of(departure1, departure2));
        DeparturesService departuresService = new DeparturesService(departuresRepository, peopleRepository);
        Assertions.assertEquals("Type1", departuresService.findAll().get(0).getType());
        Assertions.assertEquals("Type2", departuresService.findAll().get(1).getType());
    }

    @Test
    void findOne() {
        Departure departure1 = new Departure("Type1", new Date());
        Departure departure2 = new Departure("Type2", new Date());

        departure1.setId(1);
        departure2.setId(2);

        Mockito.doReturn(Optional.of(departure1)).when(departuresRepository).findById(1);
        Mockito.doReturn(Optional.of(departure2)).when(departuresRepository).findById(2);

        DeparturesService departuresService = new DeparturesService(departuresRepository, peopleRepository);
        Assertions.assertEquals("Type1", departuresService.findOne(1).getType());
        Assertions.assertEquals("Type2", departuresService.findOne(2).getType());
    }

    @Test
    void delete() {

        DeparturesService departuresService = new DeparturesService(departuresRepository, peopleRepository);
        departuresService.delete(1);

        Mockito.verify(departuresRepository).deleteById(captor.capture());

        Integer captured = captor.getValue();

        assertEquals(1, captured);
    }
}