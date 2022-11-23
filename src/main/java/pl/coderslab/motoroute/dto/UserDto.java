package pl.coderslab.motoroute.dto;

import lombok.Data;
import pl.coderslab.motoroute.security.PasswordMatches;
import pl.coderslab.motoroute.security.UniqueEmail;
import pl.coderslab.motoroute.security.UniqueUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@PasswordMatches(message = "Hasło nie pasuje")
@UniqueEmail(message = "Podany email już istnieje")
@UniqueUsername(message = "Podany użytkownik już istnieje")
public class UserDto {

    @NotEmpty(message = "Nazwa użytkownika jest wymagana")
    @Size(min = 3, max = 20, message = "Nazwa musi zawierać 3-20 znaków")
    private String username;

    @NotEmpty(message = "Email jest wymagany")
    @Email(message = "Niepoprawny email")
    private String email;

    @NotEmpty(message = "Hasło jest wymagane")
    private String password;

    @NotEmpty(message = "Potwierdź hasło")
//    @PasswordMatches(message = "Hasło nie pasuje")
    private String matchingPassword;
}
