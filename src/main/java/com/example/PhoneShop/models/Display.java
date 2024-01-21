package com.example.PhoneShop.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "display")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Slf4j
public class Display {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private float diagonal;

    private int resolutionX;

    private int resolutionY;

    @Column(name = "type_matrix")
    private String typeMatrix;

    private int rate;


    public String getResolution() {
        return resolutionX + "x" + resolutionY;
    }
}
