package com.pizzani.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pizzani.entities.Confirmation;

public interface ConfirmationRepository extends JpaRepository<Confirmation, Long>{
	
	public Confirmation findByToken(String token);
}
