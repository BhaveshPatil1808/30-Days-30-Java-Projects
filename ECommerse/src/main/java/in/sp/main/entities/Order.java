package in.sp.main.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")  // FK to customers.id
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id")  // FK to products.id
    private Product product;


    private String status;

	private double totalAmount;
	
	
	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	

	

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
	public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public Order(int id, Customer customer, Product products, double totalAmount, String status) {
        this.id = id;
        this.customer = customer;
        this.product = products;
        this.totalAmount = totalAmount;
        this.status = status;
    }
    public void show() {
    	System.out.println("-------------------------------------------");
    	System.out.println("ID            :"+id);
    	System.out.println("Customer id   :"+customer.getId());
    	System.out.println("Customer Name :"+customer.getName());
    	System.out.println("Product Id    :"+product.getId());
    	System.out.println("Product Name  :"+product.getName());
    	System.out.println("Status        :"+status);
    	System.out.println("Total Amount  :"+totalAmount);
    	System.out.println("-------------------------------------------");
    }
}
