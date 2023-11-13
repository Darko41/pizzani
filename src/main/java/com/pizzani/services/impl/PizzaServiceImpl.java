package com.pizzani.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pizzani.entities.Pizza;
import com.pizzani.repositories.PizzaRepository;
import com.pizzani.services.PizzaService;

@Service
public class PizzaServiceImpl implements PizzaService {
	
	private PizzaRepository pizzaRepository;
	
	public PizzaServiceImpl(PizzaRepository pizzaRepository) {
		super();
		this.pizzaRepository = pizzaRepository;
	}

	@Override
	public List<Pizza> getAllPizzas() {
		return pizzaRepository.findAll();
	}
	
	@Override
	public List<Pizza> getStandOutPizzas() {

		List<Pizza> pizzas = pizzaRepository.findAll();
		
		List<Pizza> pizzasSO = pizzas.stream()
				.filter(pizza -> pizza.isStandOut())
				.collect(Collectors.toList());
		
		System.out.println(pizzasSO);
		
		return pizzasSO;
	}
	
	@Override
	public Pizza getPizzaById(Long id) {
//		return pizzaRepository.getReferenceById(id);	// moze i ovako
		return pizzaRepository.findById(id).get();
	}

	@Override
	public Pizza savePizza(Pizza pizza) {
		return pizzaRepository.save(pizza);
	}

	@Override
	public Pizza updatePizza(Pizza pizza) {
		return pizzaRepository.save(pizza);
	}

	@Override
	public void deletePizzaById(Long id) {
		pizzaRepository.deleteById(id);
	}

	@Override
	public void setStandOut(Long id) {
		Pizza pizza = pizzaRepository.findById(id).get();
		pizza.setStandOut(true);
	}

	@Override
	public void setNotStandOut(Long id) {
		Pizza pizza = pizzaRepository.findById(id).get();
		pizza.setStandOut(false);
	}
	

}
