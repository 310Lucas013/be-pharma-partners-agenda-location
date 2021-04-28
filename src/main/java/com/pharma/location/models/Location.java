package com.pharma.location.models;


import javax.persistence.*;

@Entity
@Table

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

    public Location(LocationDto dto) {
        this.id = dto.getId();
        this.street = dto.getStreet();
        this.houseNumber = dto.getHouseNumber();
        this.zipCode = dto.getZipCode();
        this.city = dto.getCity();
        this.country = dto.getCountry();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
