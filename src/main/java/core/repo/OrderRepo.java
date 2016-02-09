package core.repo;

import java.util.List;

import core.entities.Orders;

public interface OrderRepo {

	public List<Orders> getAllOrders();
	public Orders createOrder(Orders order);
}
