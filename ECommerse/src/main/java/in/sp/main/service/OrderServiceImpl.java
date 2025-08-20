package in.sp.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.entities.Order;
import in.sp.main.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository repository;
	
	
	@Override
	public int saveOrder(Order order) {
		this.repository.save(order);
		return 1;
	}

	@Override
	public List<Order> getAllOrders() {
		return this.repository.findAll();
	}

	@Override
	public Order getOrderById(int id) {
		Optional<Order> order = this.repository.findById(id);
		if(order.isPresent()) return order.get();
		return null;
	}

	@Override
	public int deleteOrder(int id) {
		this.repository.deleteById(id);
		return 1;
	}

	
}
