package com.example.javamireaprac18.utils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
@Aspect
public class TimeLoggingAspect {

    @Before("allServiceMethods()")
    public void logMethodTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        log.info("Time: {}", dtf.format(now));
    }


    @Pointcut("within(com.example.javamireaprac18.services.*)")
    public void allServiceMethods() {

    }
}
