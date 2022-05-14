package pl.edu.pwr.concertbooker.service.event.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class EventInfoDto {
    long id;
    String name;
    LocalDateTime date;
    String description;
}
