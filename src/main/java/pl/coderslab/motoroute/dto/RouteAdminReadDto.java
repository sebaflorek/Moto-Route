package pl.coderslab.motoroute.dto;

import lombok.Data;
import pl.coderslab.motoroute.entity.Region;
import pl.coderslab.motoroute.entity.Type;

import javax.validation.constraints.*;

@Data
public class RouteAdminReadDto {
    private long id;
    private String name;
    private String created;
    private String updated;
    private long authorId;

}
