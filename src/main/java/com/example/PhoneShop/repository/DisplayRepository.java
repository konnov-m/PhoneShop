package com.example.PhoneShop.repository;

import com.example.PhoneShop.models.Display;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DisplayRepository extends CrudRepository<Display, Long> {
    Optional<Display> findByName(String name);
}
