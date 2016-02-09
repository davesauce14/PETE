package core.repo;

import java.util.List;

import core.entities.Appetizer;
import core.entities.Topping;

public interface AppetizerRepo {
	public List<Appetizer> getAllAppitizers();
	public Appetizer createAppetizer(Appetizer app);
	public Appetizer getAppetizerById(Long id);
	public Appetizer getAppetizerByName(String appetizerName);
	
	public List<Topping> getAllToppings();
	public Topping createTopping(Topping top);
	
	
}
