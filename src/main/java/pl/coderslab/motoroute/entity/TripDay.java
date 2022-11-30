package pl.coderslab.motoroute.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = TripDay.TABLE_NAME)
@Data
public class TripDay {
    public static final String TABLE_NAME = "tripdays";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Min(1)
    private int dayNumber;

    @OneToOne
    @JoinColumn(name = "route_id")
    @NotNull(message = "{invalid.route.route-notnull}")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    @NotNull(message = "{invalid.trip.trip-notnull}")
    private Trip trip;



}
