package pl.coderslab.motoroute.dto;

import lombok.Data;
import pl.coderslab.motoroute.entity.Region;
import pl.coderslab.motoroute.entity.Type;

import javax.validation.constraints.*;

@Data
public class RouteCreateDto {

    @NotEmpty
    private String name;

    @Min(1)
    private int distance;

    @NotEmpty
    @Size(max = 500, message = "{invalid.description.description-length}")
    private String description;

    @NotNull(message = "{invalid.region.region-notnull}")
    private Region region;

    @NotNull(message = "{invalid.type.type-notnull}")
    private Type type;

    @Size(max = 2000)
    @Pattern(regexp = "https:\\/\\/maps\\.openrouteservice\\.org\\/\\#\\/directions\\/(\\S+)", message = "{invalid.map.map-pattern}")
    private String map;

    private long authorId;

}
