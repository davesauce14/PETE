package core.repo.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import core.entities.Orders;
import core.repo.OrderRepo;

@Repository
public class JpaOrderRepo implements OrderRepo{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Orders> getAllOrders() {
		Query query = em.createQuery("SELECT o FROM Orders o");
		return query.getResultList();
	}

	@Override
	public Orders createOrder(Orders order) {
		em.persist(order);
		return order;
	}

}
