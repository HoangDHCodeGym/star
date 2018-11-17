package com.codegym.star.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name="startable")
@Data
public class Star {
    @Id
    @NotNull(message = "Star code should not empty")
    private String code;
    private String name;
    private String type;
    private String galaxy;
    @Positive(message = "Luminosity must be greater than zero")
    private Double luminosity;
    @Positive(message = "Distance must be greater than zero")
    private Double distance;
}
