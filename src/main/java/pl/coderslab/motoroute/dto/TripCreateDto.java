package pl.coderslab.motoroute.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class TripCreateDto {

    @NotEmpty
    @Size(min = 3, max = 30, message = "{invalid.name.name-length}")
    private String name;

    @Size(max = 500, message = "{invalid.description.description-length}")
    private String description;

}
