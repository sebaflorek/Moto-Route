package pl.coderslab.motoroute.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserReadDto {

    private String username;
    private String email;

}
