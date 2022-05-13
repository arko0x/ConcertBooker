package pl.edu.pwr.concertbooker.model;

import lombok.Getter;
import lombok.Setter;
import pl.edu.pwr.concertbooker.model.enums.TicketType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Setter
    private TicketType type;

    @ManyToOne
    private Seat seat;

}
