package pl.coderslab.motoroute.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = User.TABLE_NAME)
@Data
public class User {
    public static final String TABLE_NAME = "users";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 60)
    @NotEmpty(message = "Nazwa użytkownika jest wymagana")
    private String username;

    @Column(nullable = false)
    @NotEmpty(message = "Hasło jest wymagane")
    private String password;

    @Column(nullable = false, unique = true, length = 60)
    @NotEmpty(message = "Email jest wymagany")
    @Email(message = "Niepoprawny email")
    private String email;

    private int enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @ManyToMany
    @JoinTable(name = "users_routes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "route_id"))
    private List<Route> favouriteRoutes;


}