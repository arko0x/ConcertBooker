package pl.edu.pwr.concertbooker.service.event.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class CreateEventDto {
    @NotNull
    private String name;
    @NotNull
    private LocalDateTime date;
    @NotNull
    private String description;
}
