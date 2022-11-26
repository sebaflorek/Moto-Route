package pl.coderslab.motoroute.dto;

import lombok.Data;
import pl.coderslab.motoroute.validation.PasswordMatches;
import pl.coderslab.motoroute.validation.UniqueEmail;
import pl.coderslab.motoroute.validation.UniqueUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@PasswordMatches
@UniqueEmail
@UniqueUsername
public class UserCreateDto {

    @NotEmpty
    @Size(min = 3, max = 20, message = "{invalid.username.username-length}")
    private String username;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String matchingPassword;
}
