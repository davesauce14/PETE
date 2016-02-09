package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import core.entities.Account;
import core.entities.Orders;
import core.service.CustomerService;
import core.service.exception.CustomerDoesntExistException;
import rest.exception.ResourceNotFound;
import rest.vo.CustomerVo;
import rest.vo.OrderVo;

@Component
@Path("customer")
public class CustomerCotroller {
	
	@Autowired
	public CustomerService customerService;
	
	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("get-customers")
	public List<Account> getCustomers(){
		return customerService.getAllCustomers();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("create-customer")
	public Account createCustomer(Account customer){
		return customerService.createCustomer(customer);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("create-stripe-customer")
	public Account createStripeCustomer(CustomerVo customer){
		
		customer.setStripeToken(customerService.createStripeCustomer(customer));
		Account account = customer.convert();
		return customerService.createCustomer(account);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("payment/{username}/{total}")
	public Boolean stripePayment(@PathParam("username")String username, @PathParam("total")int total) throws ResourceNotFound{
		try {
			return customerService.createStripePayment(username, total);
		} catch (Exception e) {
			System.out.println("error in stripe pmt");
			throw new ResourceNotFound();
		}
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("login/{username}/{password}")
	public Account authenticateUser(@PathParam("username")String username, @PathParam("password")String password) throws ResourceNotFound{
		try {
			Account customer = customerService.authenticateUser(username, password);
			return customer;
		} catch (Exception e) {
			System.out.println("not in db");
			throw new ResourceNotFound();
		}
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("checkout/{username}")
	public Orders checkout(Orders order, @PathParam("username") String username){
		customerService.createOrder(order, username);
		return order;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("test")
	public List<OrderVo> test(){
		System.out.println("hi");
		List<Orders> orders = (List<Orders>) customerService.getAllOrders();
		List<OrderVo> orderListVo = new ArrayList<OrderVo>();
		for(Orders order : orders){
			orderListVo.add(order.convertToVo());
		}
		return orderListVo;
	}
}
