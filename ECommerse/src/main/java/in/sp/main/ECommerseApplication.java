package in.sp.main;

import java.util.List;
import java.util.Scanner;
import in.sp.main.service.ProductServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import in.sp.main.entities.Customer;
import in.sp.main.entities.Order;
import in.sp.main.entities.Product;
import in.sp.main.repository.CustomerRepository;
import in.sp.main.service.CustomerService;
import in.sp.main.service.OrderService;
import in.sp.main.service.OrderServiceImpl;
import in.sp.main.service.ProductService;

@SpringBootApplication
public class ECommerseApplication {

	private final CustomerRepository customerRepository;

	private final ProductServiceImpl productServiceImpl;

	private final OrderServiceImpl orderServiceImpl;

	ECommerseApplication(OrderServiceImpl orderServiceImpl, ProductServiceImpl productServiceImpl,
			CustomerRepository customerRepository) {
		this.orderServiceImpl = orderServiceImpl;
		this.productServiceImpl = productServiceImpl;
		this.customerRepository = customerRepository;
	}

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext context = SpringApplication.run(ECommerseApplication.class, args);

		// Get beans from Spring context
		ProductService productService = context.getBean(ProductService.class);
		CustomerService customerService = context.getBean(CustomerService.class);
		OrderService orderService = context.getBean(OrderService.class);

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("\n=== E-Commerce Application ===");
			System.out.println("1. Product Panel");
			System.out.println("2. Customer Panel");
			System.out.println("3. Order Panel");
			System.out.println("0. Exit");
			System.out.print("Enter choice: ");

			int choice = sc.nextInt();

			switch (choice) {
			case 1 -> productPanel(productService, sc);
			case 2 -> customerPanel(customerService, sc);
			case 3 -> orderPanel(orderService, customerService, productService, sc);
			case 0 -> {
				
				System.out.println("Exiting...");
				Thread.sleep(1000);
				
				System.out.println("...........Thanks Visit Again......");
				sc.close();
				SpringApplication.exit(context); // close Spring Boot properly
				return;
			}
			default -> System.out.println("Invalid choice. Try again.");
			}
		}
	}

	// Product panel
	private static void productPanel(ProductService service, Scanner sc) {
		while (true) {
			System.out.println("\n--- Product Panel ---");
			System.out.println("1. Add Product");
			System.out.println("2. List Products");
			System.out.println("3. Delete Product");
			System.out.println("4. Update Product");
			System.out.println("0. Back");
			System.out.print("Enter choice: ");
			int choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1:
				Product p = new Product();
				System.out.println("Enter Name:");
				p.setName(sc.nextLine());
				System.out.println("Enter Price ");
				p.setPrice(sc.nextDouble());
				sc.nextLine();
				System.out.println("Enter Category:");
				p.setCategory(sc.nextLine());
				service.addProduct(p);
				break;
			case 2:
				List<Product> list = service.getAllProducts();
				for (Product x : list) {
					x.show();
				}
				break;
			case 3:
				System.out.println("Enter Product Id:");
				service.deleteProduct(sc.nextInt());
				break;
			case 4:
				System.out.println("Enter Product Id:");
				int id = sc.nextInt();
				Product o = service.getProductById(id);
				System.out.println("Enter Product Name:");
				sc.nextLine();
				o.setName(sc.nextLine());
				System.out.println("Enter Product Price:");
				o.setPrice(sc.nextDouble());
				System.out.println("Enter Product Category:");
				sc.nextLine();
				o.setCategory(sc.nextLine());
				service.updateProduct(id, o.getName(), o.getPrice(), o.getCategory());
				System.out.println("Product Updated......");
				o.show();
				break;
			case 0:
				return;
			default:
				System.out.println("Invalid choice.");
			}
		}
	}

	// Customer panel
	private static void customerPanel(CustomerService service, Scanner sc) {
		while (true) {
			System.out.println("\n--- Customer Panel ---");
			System.out.println("1. Add Customer");
			System.out.println("2. List Customers");
			System.out.println("3. Update Customer");
			System.out.println("4. Delete Customer");
			System.out.println("0. Back");
			System.out.print("Enter choice: ");
			int choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1:
				Customer c = new Customer();
				System.out.println("Enter Customer Name:");
				c.setName(sc.nextLine());
				System.out.println("Enter Customer Email:");
				c.setEmail(sc.nextLine());
				System.out.println("Enter Customer Address:");
				c.setAddress(sc.nextLine());
				System.out.println("Enter Customer Phone Number:");
				c.setPhone(sc.nextLine());

				service.addCustomer(c);
				c.show();
				break;
			case 2:
				List<Customer> list = service.getAllCustomers();
				for (Customer c1 : list) {
					c1.show();
				}
				break;

			case 3:
				System.out.println("Enter id:");
				int id = sc.nextInt();
				Customer customer = service.getCustomerById(id);
				System.out.println("Enter Name:");
				customer.setName(sc.nextLine());
				System.out.println("Enter Address:");
				customer.setAddress(sc.nextLine());
				System.out.println("Enter Email:");
				customer.setEmail(sc.nextLine());
				System.out.println("Enter Phone:");
				customer.setPhone(sc.nextLine());

				service.updateCustomer(id, customer.getName(), customer.getEmail(), customer.getPhone(),
						customer.getAddress());
				System.out.println("Customer Updated....");
				customer.show();
				break;
			case 4:
				System.out.println("Enter Id:");
				service.deleteCustomer(sc.nextInt());
			case 0:
				return;

			default:
				System.out.println("Invalid choice.");
				break;
			}
		}
	}

	// Order panel
	private static void orderPanel(OrderService service, CustomerService custService, ProductService pService,
			Scanner sc) {
		while (true) {
			System.out.println("\n--- Order Panel ---");
			System.out.println("1. Place Order");
			System.out.println("2. List Orders");
			System.out.println("3. Delete Order");
			System.out.println("0. Back");
			System.out.print("Enter choice: ");
			int choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1:
				List<Order> list = service.getAllOrders();
				for (Order s : list) {
					s.show();
				}

				Order o = new Order();
				System.out.println("Enter Customer Id:");
				o.setCustomer(custService.getCustomerById(sc.nextInt()));
				System.out.println("Enter Product Id:");
				int id1 = sc.nextInt();
				o.setProduct(pService.getProductById(id1));
				o.setStatus("Order Placed");
				System.out.println("Enter Amount:");
				double amount = sc.nextDouble();
				if (amount == pService.getProductById(id1).getPrice()) {
					o.setTotalAmount(amount);
					System.out.println("Confirm Order Y/N:");
					char ch = sc.next().charAt(0);
					if (ch == 'y' || ch == 'Y') {
						service.saveOrder(o);
						System.out.println("Order Placed Sucessfully..");
						System.out.println("Order Details..");
						o.show();
					} else {
						System.out.println("Order Not Placed");
					}
				}else {
					System.out.println("Balance Insufficent....");
				}

				break;
			case 2:
				List<Order> list1 = service.getAllOrders();
				for (Order s : list1) {
					s.show();
				}
				break;
			case 3:
				System.out.println("Enter Order Id:");
				service.deleteOrder(sc.nextInt());
				System.out.println("\nOrder Deleted..");
				break;
			case 0:

				return;
			default:
				System.out.println("Invalid choice.");
			}
		}
	}

}
