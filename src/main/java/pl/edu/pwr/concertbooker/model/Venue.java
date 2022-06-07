package pl.edu.pwr.concertbooker.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Getter
@Setter
public class Venue {
    @Id
    @GeneratedValue
    private long Id;

    @NotNull
    private String name;
    
    @NotNull
    private String address;

    @OneToMany(mappedBy = "venue", cascade = CascadeType.REMOVE)
    private Collection<Sector> sectors;

    @OneToMany(mappedBy = "venue")
    private Collection<Event> events;

    @NotNull
    public String seatPattern;
}
