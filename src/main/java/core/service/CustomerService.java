package core.service;

import java.util.List;

import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Customer;

import core.entities.Account;
import core.entities.Orders;
import rest.vo.CustomerVo;

public interface CustomerService {
	public Account createCustomer(Account customer);
	public Account getCustomerById(Long id);
	public Account findAccountByUserName(String username);
	public List<Account> getAllCustomers();
	public Account authenticateUser(String username, String password);
	public String createStripeCustomer(CustomerVo customer);
	public Boolean createStripePayment(String username, int total) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException;
	
	public List<Orders> getAllOrders();
	public Orders createOrder(Orders order);
	public Orders createOrder(Orders order, String userId);
}
