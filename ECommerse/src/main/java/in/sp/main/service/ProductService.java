package in.sp.main.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import in.sp.main.entities.Product;

@Repository
public interface ProductService {
    
	int addProduct(Product p);
    List<Product> getAllProducts();
    Product getProductById(int id);
    int deleteProduct(int id);
    Product updateProduct(int id, String newName, double newPrice,String Category);
}

