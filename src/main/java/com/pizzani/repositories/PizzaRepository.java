package com.pizzani.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pizzani.entities.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {

}
