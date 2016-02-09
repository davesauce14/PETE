package core.repo.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import core.entities.Appetizer;
import core.entities.Topping;
import core.repo.AppetizerRepo;

@Repository
public class JpaAppetizerRepo implements AppetizerRepo {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Appetizer> getAllAppitizers() {
		Query query = em.createQuery("SELECT a FROM Appetizer a");
		return query.getResultList();
	}

	@Override
	public Appetizer createAppetizer(Appetizer app) {
		em.persist(app);
		return app;
	}

	@Override
	public Appetizer getAppetizerById(Long id) {
		Appetizer app = em.find(Appetizer.class, id);
		return app;
	}

	@Override
	public Appetizer getAppetizerByName(String appetizerName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Topping> getAllToppings() {
		Query query = em.createQuery("SELECT t FROM Topping t");
		return query.getResultList();
	}

	@Override
	public Topping createTopping(Topping top) {
		em.persist(top);
		return top;
	}

}
