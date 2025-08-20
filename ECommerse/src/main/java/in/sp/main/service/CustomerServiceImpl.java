package in.sp.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.entities.Customer;
import in.sp.main.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository repository;
	
	@Override
	public int addCustomer(Customer customer) {
		this.repository.save(customer);
		return 1;
	}

	@Override
	public List<Customer> getAllCustomers() {
		return this.repository.findAll();
	}

	@Override
	public Customer getCustomerById(int id) {
		Optional<Customer> cutomer = this.repository.findById(id);
		if(cutomer.isPresent()) {
			return cutomer.get();
		}
		return null;
	}

	@Override
	public int updateCustomer(int id, String name, String email, String phone, String address) {
		Customer c = this.getCustomerById(id);
		if(c!=null) {
			c.setEmail(email);
			c.setName(name);
			c.setAddress(address);
			c.setPhone(phone);
			this.repository.save(c);
			return 1;
		}else {
			return 0;
		}
	}

	@Override
	public int deleteCustomer(int id) {
		this.repository.deleteById(id);
		return 1;
		
	}

}
