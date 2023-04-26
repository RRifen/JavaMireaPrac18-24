package com.example.javamireaprac18.services;


import com.example.javamireaprac18.models.PostOffice;
import com.example.javamireaprac18.repositories.PostOfficesRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PostOfficesServiceTest {

    @Mock
    private PostOfficesRepository postOfficesRepository;

    @Captor
    ArgumentCaptor<Integer> captor;

    @Test
    void findAll() {
        PostOffice postOffice1 = new PostOffice("PostOffice1" , "Moscow");
        PostOffice postOffice2 = new PostOffice("PostOffice2" , "Moscow");

        Mockito.when(postOfficesRepository.findAll()).thenReturn(List.of(postOffice1, postOffice2));
        PostOfficesService postOfficesService = new PostOfficesService(postOfficesRepository);
        Assertions.assertEquals("PostOffice1", postOfficesService.findAll().get(0).getName());
        Assertions.assertEquals("PostOffice2", postOfficesService.findAll().get(1).getName());
    }

    @Test
    void findOne() {

        PostOffice postOffice1 = new PostOffice("PostOffice1" , "Moscow");
        PostOffice postOffice2 = new PostOffice("PostOffice2" , "Moscow");

        postOffice1.setId(1);
        postOffice2.setId(2);

        Mockito.doReturn(Optional.of(postOffice1)).when(postOfficesRepository).findById(1);
        Mockito.doReturn(Optional.of(postOffice2)).when(postOfficesRepository).findById(2);

        PostOfficesService postOfficesService = new PostOfficesService(postOfficesRepository);
        Assertions.assertEquals("PostOffice1", postOfficesService.findOne(1).getName());
        Assertions.assertEquals("PostOffice2", postOfficesService.findOne(2).getName());
    }

    @Test
    void delete() {
        PostOfficesService postOfficesService = new PostOfficesService(postOfficesRepository);
        postOfficesService.delete(1);

        Mockito.verify(postOfficesRepository).deleteById(captor.capture());

        Integer captured = captor.getValue();

        assertEquals(1, captured);
    }
}