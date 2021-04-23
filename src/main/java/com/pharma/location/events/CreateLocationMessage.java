package com.pharma.location.events;

import lombok.Data;

@Data
public class CreateLocationMessage {
    private String streetName;
    private String houseNumber;
    private String zipCode;
    private String city;
    private String country;
}
