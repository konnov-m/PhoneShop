package com.example.PhoneShop.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * Класс компания. Свойства: <b>id</b>, <b>name</b>.
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

    private String surname;
}
