package in.sp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.main.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
