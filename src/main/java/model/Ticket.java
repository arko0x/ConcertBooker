package model;

import lombok.Getter;
import lombok.Setter;
import model.enums.TicketType;

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
