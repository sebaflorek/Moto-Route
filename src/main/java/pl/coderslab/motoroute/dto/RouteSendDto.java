package pl.coderslab.motoroute.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class RouteSendDto {

    @NotEmpty
    @Size(min = 3, max = 20, message = "{invalid.username.username-length}")
    private String name;

    @NotEmpty
    @Email
    private String email;
}
