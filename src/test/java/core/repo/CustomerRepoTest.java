package core.repo;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import core.entities.Account;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
public class CustomerRepoTest {
	
	@Autowired
	private CustomerRepo repo;
	
	private Account cust;
	
	@Before
	@Transactional
	@Rollback(false)
	public void setUpp(){
		cust = new Account();
		cust.setUserName("Bajcowasdski");
		repo.createCustomer(cust);
	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void test(){
		//assertNotNull(repo.getCustomerById(cust.getId()));
		
	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void test2(){
		List<Account> customers = repo.getAllCustomers();
		for(Account customer : customers){
			System.out.println(customer.getUserName());
		}
	}
}
