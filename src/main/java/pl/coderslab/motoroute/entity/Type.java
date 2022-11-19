package pl.coderslab.motoroute.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = Type.TABLE_NAME)
@Data
public class Type {
    public static final String TABLE_NAME = "types";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

}
