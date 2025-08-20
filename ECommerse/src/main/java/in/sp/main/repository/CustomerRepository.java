package in.sp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.main.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
