package com.example.javamireaprac18.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "Departure")
public class Departure {
    public Departure() {

    }
    public Departure(String type, Date departureDate) {
        this.type = type;
        this.departureDate = departureDate;
    }

    @Column(name = "type")
    private String type;

    @Column(name = "departure_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date departureDate;


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "post_office_id", referencedColumnName = "id")
    private PostOffice postOffice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public PostOffice getPostOffice() {
        return postOffice;
    }

    public void setPostOffice(PostOffice postOffice) {
        this.postOffice = postOffice;
    }

    @Override
    public String toString() {
        return "Departure{" +
                "type='" + type + '\'' +
                ", departureDate='" + departureDate + '\'' +
                '}';
    }
}
