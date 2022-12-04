package pl.coderslab.motoroute.dto;

import lombok.Data;
import pl.coderslab.motoroute.validation.ChangePassword;
import pl.coderslab.motoroute.validation.PasswordMatches;

import javax.validation.constraints.NotEmpty;

@Data
@ChangePassword
@PasswordMatches
public class UserPassDto {
    private long id;
    @NotEmpty
    private String oldPassword;
    @NotEmpty
    private String newPassword;
    @NotEmpty
    private String matchingNewPassword;
}
