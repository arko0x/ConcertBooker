package pl.edu.pwr.concertbooker.model;

import lombok.Getter;
import lombok.Setter;
import pl.edu.pwr.concertbooker.model.enums.SeatType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Getter
@Setter
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Setter
    @NotNull
    private int number;

    @Setter
    @NotNull
    private SeatType type;

    @OneToMany(mappedBy = "seat")
    private Collection<Ticket> tickets;

    @Setter
    @NotNull
    @ManyToOne
    @JoinColumn(name = "rower_id", nullable = false)
    private Row row;

}
