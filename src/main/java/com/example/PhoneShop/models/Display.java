package com.example.PhoneShop.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Класс дисплей. Свойства: <b>id</b>, <b>name</b>, <b>diagonal</b>, <b>resolutionX</b>, <b>resolutionY</b>
 * <b>typeMatrix</b>, <b>rate</b>.
 * @author Коннов Михаил
 */
@Entity
@Table(name = "display")
@Getter
@Setter
@ToString
@EqualsAndHashCode
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

    /**
     * Частота обновления экрана. [Гц]
     */
    private int rate;

    /**
     * Получение разрешения экрана в стандартном виде.
     * @return String
     */
    public String getResolution() {
        return resolutionX + "x" + resolutionY;
    }
}
