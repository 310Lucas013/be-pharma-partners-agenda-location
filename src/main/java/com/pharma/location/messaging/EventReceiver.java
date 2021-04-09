package com.pharma.location.messaging;

import com.google.gson.Gson;
import com.pharma.location.events.CreateAppointmentEvent;
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

    LocationService locationService;

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
        location.setId(event.getId());
        location.setCity(event.getLocation());
        location.setCountry(event.getLocation());
        location.setHouseNumber(event.getLocation());
        location.setStreet(event.getLocation());
        locationService.save(location);
    }
}
