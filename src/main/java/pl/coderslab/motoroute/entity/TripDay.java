package pl.coderslab.motoroute.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = TripDay.TABLE_NAME)
@Getter
@Setter
public class TripDay {
    public static final String TABLE_NAME = "tripdays";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int dayNumber;

    @OneToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;



}
