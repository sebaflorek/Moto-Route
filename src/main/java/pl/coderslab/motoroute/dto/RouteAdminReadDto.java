package pl.coderslab.motoroute.dto;

import lombok.Data;

@Data
public class RouteAdminReadDto {
    private long id;
    private String name;
    private String created;
    private String updated;
    private long authorId;

}
