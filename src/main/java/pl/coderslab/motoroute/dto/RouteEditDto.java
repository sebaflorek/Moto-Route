package pl.coderslab.motoroute.dto;

import lombok.Data;
import pl.coderslab.motoroute.entity.Region;
import pl.coderslab.motoroute.entity.Type;

import javax.validation.constraints.*;

@Data
public class RouteEditDto {
    private long id;

    @NotEmpty
    @Size(min = 3, max = 30, message = "{invalid.name.name-length}")
    private String name;

    @Min(1)
    private String distance;

    @Size(max = 2000)
    @Pattern(regexp = "https:\\/\\/maps\\.openrouteservice\\.org\\/\\#\\/directions\\/(\\S+)", message = "{invalid.map.map-pattern}")
    private String map;

    @NotEmpty
    @Size(max = 500, message = "{invalid.description.description-length}")
    private String description;

    @NotNull(message = "{invalid.region.region-notnull}")
    private Region region;

    @NotNull(message = "{invalid.type.type-notnull}")
    private Type type;

    private long authorId;

}
