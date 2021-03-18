package com.pharma.location.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String street;
    @Column
    private String houseNumber;
    @Column
    private String zipCode;
    @Column
    private String city;
    @Column
    private String country;

    public Location() {

    }

    public Location(Long id, String street, String houseNumber, String zipCode, String city, String country) {
        this.id = id;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
    }
}
