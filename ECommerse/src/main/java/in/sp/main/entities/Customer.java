package in.sp.main.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String email;
	
	private String address;
	
	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer(int id, String name, String email, String address, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void show() {
		System.out.println("-----------------------------------------------------");
		System.out.println("Id               :"+id);
		System.out.println("Customer Name    :"+name);
		System.out.println("Customer Email   :"+email);
		System.out.println("Customer Address :"+address);
		System.out.println("Customer Phone   :"+phone);
		System.out.println("-----------------------------------------------------");
	}
	
	
}
