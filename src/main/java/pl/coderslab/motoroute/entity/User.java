package pl.coderslab.motoroute.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = User.TABLE_NAME)
@Data
public class User {
    public static final String TABLE_NAME = "users";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 60)
    private String name;

    @Column(nullable = false, unique = true, length = 60)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(name = "users_routes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "route_id"))
    private List<Route> favouriteRoutes;







}
