package pl.coderslab.motoroute.dto;

import lombok.Data;
import pl.coderslab.motoroute.validation.ForgotPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@ForgotPassword
public class UserForgotPassDto {
    @NotEmpty
    @Email
    private String email;

}
