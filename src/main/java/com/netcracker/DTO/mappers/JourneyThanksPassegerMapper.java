package com.netcracker.DTO.mappers;

import com.netcracker.DTO.JourneyThanksPassegerDTO;
import com.netcracker.DTO.NotificationDTO;
import com.netcracker.entities.Journey;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class JourneyThanksPassegerMapper extends AbstractMapper<Journey, JourneyThanksPassegerDTO> {

    public JourneyThanksPassegerMapper() {
        super(Journey.class, JourneyThanksPassegerDTO.class);
    }

    @PostConstruct
    public void setupMapper () {
        mapper.typeMap(Journey.class, JourneyThanksPassegerDTO.class).addMappings(mapper -> {
                    mapper.map(Journey::getDate, JourneyThanksPassegerDTO::setDate);
                    mapper.map(source -> source.getRoute().getRouteId(), JourneyThanksPassegerDTO::setRouteId);
                    mapper.map(source -> source.getRoute().getPrice(), JourneyThanksPassegerDTO::setPrice);
                }
        );
    }
}
