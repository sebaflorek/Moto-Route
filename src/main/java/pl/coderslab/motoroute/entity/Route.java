package pl.coderslab.motoroute.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = Route.TABLE_NAME)
@Data
public class Route {
    public static final String TABLE_NAME = "routes";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private int distance;

    private String map;

    private String description;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    private int popularity;

    private int likes;

    private LocalDateTime created;

    private LocalDateTime updated;

    private long authorId;

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updated = LocalDateTime.now();
    }




}
