package com.example.javamireaprac18.services;

import com.example.javamireaprac18.models.Departure;
import com.example.javamireaprac18.models.PostOffice;
import com.example.javamireaprac18.repositories.DeparturesRepository;
import com.example.javamireaprac18.repositories.PostOfficesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class SchedularService {

    private final DeparturesRepository departuresRepository;
    private final PostOfficesRepository postOfficesRepository;

    @Autowired
    public SchedularService(DeparturesRepository departuresRepository, PostOfficesRepository postOfficesRepository) {
        this.departuresRepository = departuresRepository;
        this.postOfficesRepository = postOfficesRepository;
    }


    @Transactional(readOnly = true)
    @Scheduled(fixedDelay = 1800000)
    public void reCreateFiles() {
        String filesDirectory = System.getProperty("user.dir") + "\\databaseFiles";

        File dir = new File(filesDirectory);
        for(File file : Objects.requireNonNull(dir.listFiles()))
            if (!file.isDirectory())
                file.delete();

        File objectFile;
        FileWriter fileWriter;


        List<Departure> departures = departuresRepository.findAll();

        for(Departure departure : departures) {
            objectFile = new File(filesDirectory + "\\departure" + departure.getId() + ".txt");
            try {
                objectFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                fileWriter = new FileWriter(objectFile);
                fileWriter.write(departure.toString() + "\n");
                fileWriter.write("Id: " + departure.getId() + "\n");
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        List<PostOffice> postOffices = postOfficesRepository.findAll();
        List<Departure> departuresInPostOffice;

        for(PostOffice postOffice : postOffices) {
            objectFile = new File(filesDirectory + "\\postOffice" + postOffice.getId() + ".txt");
            try {
                objectFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                fileWriter = new FileWriter(objectFile);
                fileWriter.write(postOffice.toString() + "\n");
                fileWriter.write("Id: " + postOffice.getId() + "\n");

                departuresInPostOffice = postOffice.getDepartures();
                fileWriter.write("Departures in Post Office: " + departuresInPostOffice);
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
