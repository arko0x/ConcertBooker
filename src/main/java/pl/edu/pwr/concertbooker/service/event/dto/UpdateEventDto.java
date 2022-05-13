package pl.edu.pwr.concertbooker.service.event.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateEventDto {
    private long id;
    private String name;
    private LocalDateTime date;
    private String description;
}
