package pl.edu.pwr.concertbooker.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Getter
@Table(name = "ROWER")
public class Row {
    @Id
    @GeneratedValue
    private long id;

    @Setter
    @NotNull
    private String name;

    @OneToMany(mappedBy = "row")
    private Collection<Seat> seats;

    @ManyToOne
    @JoinColumn(name="sector_id", nullable = false)
    private Sector sector;
}
