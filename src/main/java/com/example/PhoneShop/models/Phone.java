package com.example.PhoneShop.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;


/**
 * Класс телефон. Свойства: <b>id</b>, <b>name</b>, <b>description</b>, <b>company</b>.
 * @author Коннов Михаил
 */
@Entity
@Table(name = "phone")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Slf4j
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
}
