package in.sp.main.service;

import java.util.List;

import in.sp.main.entities.Customer;

public interface CustomerService {

	int addCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(int id);
    int updateCustomer(int id, String name, String email, String phone, String address);
    int deleteCustomer(int id);
}
