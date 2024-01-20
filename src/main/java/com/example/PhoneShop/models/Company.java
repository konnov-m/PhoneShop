package com.example.PhoneShop.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Класс компания. Свойства: <b>id</b>, <b>title</b>.
 * @author Коннов Михаил
 */
@Entity
@Table(name = "company")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Slf4j
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Title shouldn't be empty")
    private String title;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "company")
    private List<Phone> phones;
}
