package com.example.javamireaprac18;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableMBeanExport
public class JavaMireaPrac18Application {

    public static void main(String[] args) {
        SpringApplication.run(JavaMireaPrac18Application.class, args);
    }

}
