package com.example.javamireaprac18.services;

import com.example.javamireaprac18.models.Person;
import com.example.javamireaprac18.repositories.PeopleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

    @Mock
    private PeopleRepository peopleRepository;

    @Captor
    ArgumentCaptor<Person> captor;

    @Test
    void register() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Person person = new Person("test_user");
        person.setPassword("123");

        RegistrationService registrationService = new RegistrationService(peopleRepository, passwordEncoder);
        registrationService.register(person);

        Mockito.verify(peopleRepository).save(captor.capture());

        Person captured = captor.getValue();

        assertEquals("ROLE_USER", captured.getRole());
    }
}