package pl.edu.pwr.concertbooker.model;

import lombok.Getter;
import lombok.Setter;
import pl.edu.pwr.concertbooker.model.enums.TicketType;
import pl.edu.pwr.concertbooker.security.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Setter
    private TicketType type;

    @ManyToOne
    @JoinColumn(name="seat_id", nullable = false)
    private Seat seat;

    @ManyToOne
    @JoinColumn(name="event_id", nullable = false)
    private Event event;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
