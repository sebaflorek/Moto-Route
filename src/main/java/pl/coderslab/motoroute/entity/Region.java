package pl.coderslab.motoroute.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = Region.TABLE_NAME)
@Data
public class Region {
    public static final String TABLE_NAME = "regions";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String city;

}
