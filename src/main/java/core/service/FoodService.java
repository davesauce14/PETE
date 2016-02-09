package core.service;

import java.util.List;

import core.entities.Appetizer;
import core.entities.Topping;

public interface FoodService {
	public Appetizer createAppetizer(Appetizer appetizer);
	public List<Appetizer> getAllAppitizers();
	public Appetizer getAppetizerById(Long id);
	public Appetizer getAppetizerByName(String appetizerName);
	
	public List<Topping> getAllToppings();
	public Topping createTopping(Topping top);
}
