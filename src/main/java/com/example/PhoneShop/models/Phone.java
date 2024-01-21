package com.example.PhoneShop.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * Класс телефон. Свойства: <b>id</b>, <b>name</b>, <b>description</b>, <b>company</b>, <b>display</b>.
 * @author Коннов Михаил
 */
@Entity
@Table(name = "phone")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name shouldn't be empty")
    private String name;

    private String description;

    /**
     * Поле компании производителя телефона {@link Company}
     */
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    /**
     * Поле экран телефона {@link Display}
     */
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "display_id", referencedColumnName = "id")
    private Display display;
}
