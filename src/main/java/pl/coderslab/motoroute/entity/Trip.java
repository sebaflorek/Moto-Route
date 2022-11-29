package pl.coderslab.motoroute.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

//@Entity
//@Table(name = Trip.TABLE_NAME)
@Data
public class Trip {
    public static final String TABLE_NAME = "trips";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Size(min = 3, max = 30, message = "{invalid.name.name-length}")
    private String name;

    @Size(max = 500, message = "{invalid.description.description-length}")
    private String description;

    @Range(min = 2, max = 14, message = "{invalid.numberOfDays.numberOfDays}")
    private int numberOfDays;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    private LocalDateTime created;

//    @PrePersist
//    public void prePersist() {
//        created = LocalDateTime.now();
//    }
}
