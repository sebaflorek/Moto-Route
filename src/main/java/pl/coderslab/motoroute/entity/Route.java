package pl.coderslab.motoroute.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = Route.TABLE_NAME)
@Data
public class Route {

    /* UWAGA: USUNĄĆ WALIDACJĘ JAK BĘDZIE MAPSTRUCT I UserEditDto */
    public static final String TABLE_NAME = "routes";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String name;

    @Min(1)
    private int distance;

    @Column(length = 2000)
    @Size(max = 2000)
    @Pattern(regexp = "https:\\/\\/maps\\.openrouteservice\\.org\\/\\#\\/directions\\/(\\S+)", message = "{invalid.map.map-pattern}")
    private String map;

    @Column(length = 500)
    @NotEmpty
    @Size(max = 500, message = "{invalid.description.description-length}")
    private String description;

    @ManyToOne
    @JoinColumn(name = "region_id")
    @NotNull(message = "{invalid.region.region-notnull}")
    private Region region;

    @ManyToOne
    @JoinColumn(name = "type_id")
    @NotNull(message = "{invalid.type.type-notnull}")
    private Type type;

    private int popularity;

    private int likes;

    private LocalDateTime created;

    private LocalDateTime updated;

    private long authorId;

    //@ManyToMany(mappedBy = "favouriteRoutes", cascade = CascadeType.ALL)
    //private List<User> favoriteUsers;

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updated = LocalDateTime.now();
    }




}
