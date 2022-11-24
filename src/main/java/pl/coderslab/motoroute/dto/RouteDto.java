package pl.coderslab.motoroute.dto;

import lombok.Data;
import pl.coderslab.motoroute.entity.Region;
import pl.coderslab.motoroute.entity.Type;

import javax.validation.constraints.*;

@Data
public class RouteDto {

    @NotEmpty
    private String name;

    @Min(1)
    private int distance;

    @NotEmpty
    @Size(max = 600)
    private String description;

    @NotNull(message = "Wybierz region")
    private Region region;

    @NotNull(message = "Wybierz typ")
    private Type type;

    @Size(max = 2000)
    @Pattern(regexp = "https:\\/\\/maps\\.openrouteservice\\.org\\/\\#\\/directions\\/(\\S+)", message = "Niepoprawny lub pusty link")
    private String map;

    private long authorId;

}
