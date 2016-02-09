package core.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class DoStuff {
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello-world");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		 try {
		 txn.begin();
		 Account cust = new Account("jimmy", "beam");
		 em.persist(cust);

		 txn.commit();
		 } catch(Exception e) {
		 if(txn != null) { txn.rollback(); }
		 e.printStackTrace();
		 } finally {
		 if(em != null) { em.close(); }
		 }
		
		
		
	}

}
