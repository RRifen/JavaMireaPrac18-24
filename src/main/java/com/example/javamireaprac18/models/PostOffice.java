package com.example.javamireaprac18.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Post_office")
public class PostOffice {

    public PostOffice() {

    }

    public PostOffice(String name, String cityName) {
        this.name = name;
        this.cityName = cityName;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "city_name")
    private String cityName;

    @OneToMany(mappedBy = "postOffice")
    private List<Departure> departures;

    public List<Departure> getDepartures() {
        return departures;
    }

    public void setDepartures(List<Departure> departures) {
        this.departures = departures;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "PostOffice{" +
                "name='" + name + '\'' +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
