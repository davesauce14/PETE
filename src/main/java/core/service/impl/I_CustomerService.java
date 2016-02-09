package core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;

import core.entities.Account;
import core.entities.Orders;
import core.repo.CustomerRepo;
import core.repo.OrderRepo;
import core.service.CustomerService;
import core.service.exception.CustomerDoesntExistException;
import rest.vo.CustomerVo;

@Service
@Transactional
public class I_CustomerService implements CustomerService{
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private OrderRepo orderRepo;

	@Override
	public List<Account> getAllCustomers() {
		return customerRepo.getAllCustomers();
	}

	@Override
	public Account createCustomer(Account customer) {
		return customerRepo.createCustomer(customer);
	}

	@Override
	public Account getCustomerById(Long id) {
		return customerRepo.getCustomerById(id);
	}

	@Override
	public Account findAccountByUserName(String username) {
		return customerRepo.findAccountByUserName(username);
	}

	@Override
	public Account authenticateUser(String username, String password) {
		Account customer = customerRepo.authenticateUser(username, password);
		if(customer == null){
			throw new CustomerDoesntExistException();
		}
		return customer; 
	}
	

	@Override
	public String createStripeCustomer(CustomerVo customer) {
		// Set your secret key: remember to change this to your live secret key in production
		// See your keys here https://dashboard.stripe.com/account/apikeys
		Stripe.apiKey = "sk_test_EJNjnu6KIfD5WU7RnliTNxga";

		// Get the credit card details submitted by the form
		String token = customer.getStripeToken();

		// Create a Customer
		Map<String, Object> customerParams = new HashMap<String, Object>();
		customerParams.put("source", token);
		customerParams.put("description", customer.getUserName());

		Customer stripeCustomer = null;
		try {
			stripeCustomer = Customer.create(customerParams);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CardException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Charge the Customer instead of the card
		Map<String, Object> chargeParams = new HashMap<String, Object>();
		chargeParams.put("amount", 1000); // amount in cents, again
		chargeParams.put("currency", "usd");
		chargeParams.put("customer", stripeCustomer.getId());

		try {
			Charge.create(chargeParams);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CardException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// YOUR CODE: Save the customer ID and other info in a database for later!

		// YOUR CODE: When it's time to charge the customer again, retrieve the customer ID!
/*
		Map<String, Object> otherChargeParams = new HashMap<String, Object>();
		otherChargeParams.put("amount", 1500); // $15.00 this time
		otherChargeParams.put("currency", "usd");
		otherChargeParams.put("customer", customerId); // Previously stored, then retrieved

		Charge.create(otherChargeParams);*/
		return stripeCustomer.getId();
	}

	@Override
	public Boolean createStripePayment(String username, int total) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Account stripeCustomer = customerRepo.getStripeCustomer(username);
		Map<String, Object> chargeParams = new HashMap<String, Object>();
		chargeParams.put("amount", total); // amount in cents, again
		chargeParams.put("currency", "usd");
		chargeParams.put("customer", stripeCustomer.getStripeId());	
		Charge charge = Charge.create(chargeParams);
		if(charge.getPaid() == true){
			return true;
		}
		return false;
	}

	@Override
	public List<Orders> getAllOrders() {
		return orderRepo.getAllOrders();
	}

	@Override
	public Orders createOrder(Orders order) {
		return orderRepo.createOrder(order);
	}
	
	@Override
	public Orders createOrder(Orders order, String userId) {
		Account account = customerRepo.findAccountByUserName(userId);
		order.setAccount(account);
		order.setOrderItemToParent();
		return createOrder(order);
	}



	

}
