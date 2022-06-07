package pl.edu.pwr.concertbooker.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Getter
@Setter
public class Sector {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String name;
    
    @OneToMany(mappedBy = "sector", cascade = CascadeType.REMOVE)
    private Collection<Row> rows;

    @NotNull
    private int rowInVenue;
    @NotNull
    private int columnInVenue;

    @ManyToOne
    @JoinColumn(name="venue_id", nullable = false)
    private Venue venue;
}
