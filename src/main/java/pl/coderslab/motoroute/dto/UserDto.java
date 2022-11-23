package pl.coderslab.motoroute.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class UserDto {

    private String username;

    @NotEmpty(message = "Email jest wymagany")
    @Email(message = "Niepoprawny email")
    private String email;

    @NotEmpty(message = "Hasło jest wymagane")
    private String password;
    private String matchingPassword;
}
