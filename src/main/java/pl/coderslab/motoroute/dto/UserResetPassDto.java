package pl.coderslab.motoroute.dto;

import lombok.Data;
import pl.coderslab.motoroute.validation.PasswordMatches;

import javax.validation.constraints.NotEmpty;

@Data
@PasswordMatches
public class UserResetPassDto {
    @NotEmpty
    private String newPassword;
    @NotEmpty
    private String matchingNewPassword;
}
