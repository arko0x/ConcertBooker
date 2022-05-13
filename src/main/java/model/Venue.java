package model;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Getter
public class Venue {
    @Id
    @GeneratedValue
    private long Id;

    @NotNull
    private String name;

    @OneToMany
    private Collection<Row> rows;
}
