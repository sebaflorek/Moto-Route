package pl.coderslab.motoroute.dto;

import lombok.Data;
import pl.coderslab.motoroute.entity.Role;

import java.util.Set;

@Data
public class UserDetailsDto {
    private long id;
    private String username;
    private String email;
    private int enabled;
    private Set<Role> roles;

}
