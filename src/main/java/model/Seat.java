package model;

import lombok.Getter;
import lombok.Setter;
import model.enums.SeatType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Getter
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

    @OneToMany
    private Collection<Ticket> tickets;

    @Setter
    @NotNull
    @ManyToOne
    private Row row;

}
