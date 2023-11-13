package com.pizzani.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pizzani.entities.Pizza;
import com.pizzani.services.PizzaService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private PizzaService pizzaService;

	public AdminController(PizzaService pizzaService) {
		super();
		this.pizzaService = pizzaService;
	}
	
	@GetMapping("/pizzas")
	public String getAllPizzasOnAdminPage(Model model) {
		model.addAttribute("pizzas", pizzaService.getAllPizzas());
		
		return "pizza-list";
	}
	
	// for opening form page
	@GetMapping("/pizzas/new")
	public String addNewPizzaForm(Model model) {
		Pizza pizza = new Pizza();
		model.addAttribute("pizza", pizza);
		
		
		return "pizza-form";
	}
	
	// for actual method call (saving new pizza)
	@PostMapping("/pizzas")
	public String savePizza(@ModelAttribute("pizza") Pizza pizza) {
		pizza.setStandOut(true);
		pizzaService.savePizza(pizza);
		return "redirect:/admin/pizzas";
	}
	
	// for opening edit page
	@GetMapping("/pizzas/edit/{id}")
	public String editPizzaForm(@PathVariable Long id, Model model) {
		model.addAttribute("pizza", pizzaService.getPizzaById(id));
		return "pizza-edit";
	}
	
	// for actual method call (editing existing pizza)
	// @PathVariable 	- to get path variable id
	// @ModelAttribute 	- to store database data to java object
	@PostMapping("/pizzas/{id}")
	public String updatePizza(@PathVariable Long id,
			@ModelAttribute("pizza") Pizza pizza, Model model) {
		
		// get pizza from database by id
		Pizza existingPizza = pizzaService.getPizzaById(id);
		existingPizza.setId(id);
		existingPizza.setName(pizza.getName());
		existingPizza.setImage(pizza.getImage());
		existingPizza.setDescription(pizza.getDescription());
		existingPizza.setIngredients(pizza.getIngredients());
		existingPizza.setStandOut(pizza.isStandOut());
		existingPizza.setPrice(pizza.getPrice());
		
		// save updated pizza object
		pizzaService.updatePizza(existingPizza);
		
		return "redirect:/admin/pizzas";
	}
	
	@GetMapping("/pizzas/{id}")
	public String deletePizza(@PathVariable Long id) {
		pizzaService.deletePizzaById(id);
		return "redirect:/admin/pizzas";
	}
	
	@GetMapping("/make-pizza-standOut")
	public String setPizzaStandOut(@RequestParam Long id) {
		pizzaService.setStandOut(id);
		return "redirect:/admin/pizzas";
	}
	
	@GetMapping("/make-pizza-not-standOut")
	public String setPizzaNotStandOut(@RequestParam Long id) {
		pizzaService.setNotStandOut(id);
		return "redirect:/admin/pizzas";
	}
	 
}
