package pl.edu.pwr.concertbooker.service.event.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class EventInfoDto {
    private long id;
    private String name;
    private LocalDateTime date;
    private String description;
    private String artist;
    private long venueId;
}
