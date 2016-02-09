package core.repo;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import core.entities.Appetizer;
import core.entities.AppetizerSize;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
public class AppetizerRepoTest {

	@Autowired
	private AppetizerRepo repo;

	private Appetizer appetizer;
	private AppetizerSize small;
	private AppetizerSize large;

	@Before
	@Transactional
	@Rollback(false)
	public void setUpp() {
		appetizer = new Appetizer();
		
		small = new AppetizerSize("Small", "2.05");
		large = new AppetizerSize("Large", "4.25");
		
		
		appetizer.setName("fries");
		appetizer.addAppetizerSize(small);
		appetizer.addAppetizerSize(large);
		repo.createAppetizer(appetizer);
	}

/*	@Test
	@Transactional
	@Rollback(false)
	public void test() {
		//assertNotNull(repo.getCustomerById(cust.getId()));

	}*/

	@Test
	@Transactional
	@Rollback(false)
	public void test2() {
		List<Appetizer> appetizers = repo.getAllAppitizers();
		for (Appetizer appetizer : appetizers ) {
			if(appetizer.getPrice() != null){
				System.out.print(appetizer.getName());
				System.out.print(" ");
				System.out.println(appetizer.getPrice());
			}else{
				List<AppetizerSize> sizes = appetizer.getAppitizerSizes();
				for (AppetizerSize size : sizes){
					System.out.print(appetizer.getName());
					System.out.print(" ");
					System.out.print(size.getSize());
					System.out.print(" ");
					System.out.println(size.getPrice());
				}
			}
		}
	}

}
