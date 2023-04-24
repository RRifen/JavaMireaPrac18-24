package com.example.javamireaprac18.services;

import com.example.javamireaprac18.models.Departure;
import com.example.javamireaprac18.models.PostOffice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSenderImpl javaMailSender;
    @Value("${mail.address.to}")
    private String mailTo;

    @Value("${mail.address.from}")
    private String mailFrom;

    @Autowired
    public EmailService(JavaMailSenderImpl javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmailMessageWithNewDeparture(Departure departure) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailFrom);
        message.setTo(mailTo);
        message.setSubject("New Departure");
        message.setText(departure.toString());
        javaMailSender.send(message);
    }

    @Async
    public void sendEmailMessageWithNewPostOffice(PostOffice postOffice) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailFrom);
        message.setTo(mailTo);
        message.setSubject("New PostOffice");
        message.setText(postOffice.toString());
        javaMailSender.send(message);
    }

}
