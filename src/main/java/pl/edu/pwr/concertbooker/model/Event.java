package pl.edu.pwr.concertbooker.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue
    private long id;

    private String name;
    private LocalDateTime date;
    private String artist;
    private String description;

    @OneToMany(mappedBy = "event")
    private Collection<Ticket> tickets;
}
