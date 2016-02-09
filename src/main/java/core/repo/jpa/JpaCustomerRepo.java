package core.repo.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import core.entities.Account;
import core.repo.CustomerRepo;

@Repository
public class JpaCustomerRepo implements CustomerRepo{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Account createCustomer(Account customer) {
		em.persist(customer);
		return customer;
	}

	@Override
	public Account getCustomerById(Long id) {
		Account found = em.find(Account.class, id);
		return found;
	}

	@Override
	public Account findAccountByUserName(String username) {
		Query query = em.createQuery("SELECT a FROM Account a WHERE a.userName = :username");
		query.setParameter("username", username);
		return (Account)query.getSingleResult();
	}

	@Override
	public List<Account> getAllCustomers() {
		Query query = em.createQuery("SELECT c FROM Customer c");
		return query.getResultList();
	}
	
	@Override
	public Account authenticateUser(String username, String password){
		Query query = em.createQuery("SELECT a FROM Account a WHERE a.userName = :username AND a.password = :password");
		query.setParameter("username", username);
		query.setParameter("password", password);
		Account cust = (Account)query.getSingleResult();
		return cust;
	}

	@Override
	public Account getStripeCustomer(String username) {
		Query query = em.createQuery("SELECT a FROM Account a WHERE a.userName = :username");
		query.setParameter("username", username);
		Account account = (Account)query.getSingleResult();
		return account;
	}
	
	

}
