package pl.edu.pwr.concertbooker.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Getter
public class Sector {
    @Id
    @GeneratedValue
    private long id;

    @Setter
    @NotNull
    private String name;
    
    @OneToMany(mappedBy = "sector")
    private Collection<Row> rows;

    @ManyToOne
    @JoinColumn(name="venue_id", nullable = false)
    private Venue venue;
}
