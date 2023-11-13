package com.pizzani.services;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pizzani.entities.Pizza;

@Repository
public interface PizzaService {

	public List<Pizza> getStandOutPizzas();
	public Pizza getPizzaById(Long id);
	public List<Pizza> getAllPizzas();
	public Pizza savePizza(Pizza pizza);
	public Pizza updatePizza(Pizza pizza);
	public void deletePizzaById(Long id);
	public void setStandOut(Long id);
	public void setNotStandOut(Long id);
}
