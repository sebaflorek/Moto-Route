package pl.coderslab.motoroute.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = Trip.TABLE_NAME)
@Data
public class Trip {
    public static final String TABLE_NAME = "trips";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Size(min = 3, max = 30, message = "{invalid.name.name-length}")
    private String name;

    @Size(max = 500, message = "{invalid.description.description-length}")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.REMOVE)
    @OrderBy("dayNumber asc")
    private List<TripDay> tripDays;

    private LocalDateTime created;

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
    }

}
