package pl.edu.pwr.concertbooker.service.event.dto;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class EventInfoDto {
    long id;
    String name;
    LocalDateTime date;
    String description;
}
