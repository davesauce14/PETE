package core.repo;

import java.util.List;

import core.entities.Account;

public interface CustomerRepo {
	public Account createCustomer(Account customer);
	public Account getCustomerById(Long id);
	public Account findAccountByUserName(String username);
	public List<Account> getAllCustomers();
	public Account authenticateUser(String username, String password);
	public Account getStripeCustomer(String username);
}
