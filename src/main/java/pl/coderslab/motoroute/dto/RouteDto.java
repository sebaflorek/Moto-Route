package pl.coderslab.motoroute.dto;

import lombok.Data;
import org.hibernate.validator.constraints.URL;
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

    @NotNull
    private Region region;

    @NotNull
    private Type type;

    @NotEmpty
    @Size(max = 2000)
    @Pattern(regexp = "https:\\/\\/maps\\.openrouteservice\\.org\\/\\#\\/directions\\/(\\S+)")
    private String map;

}
