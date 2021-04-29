package com.pharma.location.messaging;

import com.google.gson.Gson;
import com.pharma.location.events.CreateAppointmentEvent;
import com.pharma.location.events.CreateLocationMessage;
import com.pharma.location.events.UpdateAppointmentEvent;
import com.pharma.location.models.Location;
import com.pharma.location.services.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class EventReceiver {

    private final LocationService locationService;

    public EventReceiver(LocationService locationService) {
        this.locationService = locationService;
    }

    private Logger log = LoggerFactory.getLogger(EventReceiver.class);


    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @RabbitListener(queues = "create-appointment")
    public void receive(Message message) {
        System.out.println("received the event!");
        log.info("Received event in service location: {}", new String(message.getBody()));
        Gson gson = new Gson();
        String json = new String(message.getBody());
        CreateAppointmentEvent event = gson.fromJson(json, CreateAppointmentEvent.class);
        Location location = new Location();
        location.setCity(event.getCity());
        location.setCountry(event.getCountry());
        location.setHouseNumber(event.getStreetNumber());
        location.setStreet(event.getStreet());
        location.setZipCode(event.getPostalCode());
        locationService.save(location);
    }

    @RabbitListener(queues = "update-appointment")
    public void update(Message message) {
        System.out.println("received the event!");
        //log.info("Received event in service location: {}", new String(message.getBody()));
        Gson gson = new Gson();
        String json = new String(message.getBody());
        UpdateAppointmentEvent event = gson.fromJson(json, UpdateAppointmentEvent.class);
        Location location = new Location();
        System.out.println(event.getCity());
        location.setId(event.getId());
        location.setCity(event.getCity());
        location.setCountry(event.getCountry());
        location.setZipCode(event.getZipCode());
        location.setHouseNumber(event.getHouseNumber());
        location.setStreet(event.getStreet());
        locationService.save(location);
    }

    @RabbitListener(queues = "create-location")
    public void receiveLocation(Message message) {
        System.out.println("received the message!");
        log.info("Received message in service location: {}", new String(message.getBody()));
        Gson gson = new Gson();
        String idk = new String(message.getBody());
        System.out.println(idk);
        CreateLocationMessage clm = gson.fromJson(idk, CreateLocationMessage.class);
        System.out.println(clm);
        Location location = new Location();
        location.setCity(clm.getCity());
        location.setCountry(clm.getCountry());
        location.setHouseNumber(clm.getHouseNumber());
        location.setStreet(clm.getStreetName());
        location.setZipCode(clm.getZipCode());
        System.out.println(location);
        Location l = locationService.save(location);
        System.out.println(l);
    }

}
