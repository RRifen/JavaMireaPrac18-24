package com.example.javamireaprac18.services;

import com.example.javamireaprac18.models.Person;
import com.example.javamireaprac18.repositories.PeopleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PersonDetailsServiceTest {

    @Mock
    private PeopleRepository peopleRepository;

    @Test
    void loadUserByUsername() {
        Person person1 = new Person("First");

        Mockito.when(peopleRepository.findByUsername("First")).thenReturn(Optional.of(person1));
        PersonDetailsService personDetailsService = new PersonDetailsService(peopleRepository);
        Assertions.assertEquals("First", personDetailsService.loadUserByUsername("First").getUsername());

    }
}