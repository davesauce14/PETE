package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import core.entities.Appetizer;
import core.entities.AppetizerSize;
import core.entities.Topping;
import core.service.FoodService;

@Component
@Path("food")
public class FoodController {
	
	@Autowired
	FoodService foodService;	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get-appetizers")
	public List<Appetizer> getAppetizers(){
		List<Appetizer> appetizers = foodService.getAllAppitizers();	
		/*GenericEntity<List<Appetizer>> ent = new GenericEntity<List<Appetizer>> (appetizers){};
		return Response.ok(ent).build();*/
		return appetizers;		
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/createAppetizer")
	public Appetizer createAppetizer(Appetizer appetizer){
		System.out.println("hi");
		return foodService.createAppetizer(appetizer);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get-toppings")
	public List<Topping> getToppings(){
		List<Topping> toppings = foodService.getAllToppings();
		return toppings;		
	}
}
