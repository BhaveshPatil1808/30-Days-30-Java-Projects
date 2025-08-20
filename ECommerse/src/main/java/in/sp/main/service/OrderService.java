package in.sp.main.service;

import java.util.List;

import in.sp.main.entities.Order;

public interface OrderService {

	int saveOrder(Order order);
    List<Order> getAllOrders();
    Order getOrderById(int id);
    int deleteOrder(int id);
}
