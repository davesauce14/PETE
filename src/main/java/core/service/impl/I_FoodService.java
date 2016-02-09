package core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.entities.Appetizer;
import core.entities.Topping;
import core.repo.AppetizerRepo;
import core.service.FoodService;

@Service
@Transactional
public class I_FoodService implements FoodService{
	
	@Autowired
	private AppetizerRepo appetizerRepo;

	@Override
	public Appetizer createAppetizer(Appetizer appetizer) {
		return appetizerRepo.createAppetizer(appetizer);
	}

	@Override
	public List<Appetizer> getAllAppitizers() {
		return appetizerRepo.getAllAppitizers();
	}

	@Override
	public Appetizer getAppetizerById(Long id) {
		return null;
	}

	@Override
	public Appetizer getAppetizerByName(String appetizerName) {
		return null;
	}

	@Override
	public List<Topping> getAllToppings() {
		return appetizerRepo.getAllToppings();
	}

	@Override
	public Topping createTopping(Topping top) {
		return appetizerRepo.createTopping(top);
	}

}
