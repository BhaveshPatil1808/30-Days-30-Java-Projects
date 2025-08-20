package in.sp.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.entities.Product;
import in.sp.main.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repo;

	@Override
	public int addProduct(Product p) {
		this.repo.save(p);
		return 1;

	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> list = this.repo.findAll();
		return list;
	}

	@Override
	public Product getProductById(int id) {
		Optional<Product> product = this.repo.findById(id);
		if (product.isPresent()) {
			return product.get();
		}
		return null;
	}

	@Override
	public int deleteProduct(int id) {
		this.repo.deleteById(id);
		return 1;

	}

	@Override
	public Product updateProduct(int id, String newName, double newPrice,String Category) {
		Product p = this.getProductById(id);
		if(p!=null) {
			p.setName(newName);
			p.setPrice(newPrice);
			p.setCategory(Category);
			this.repo.save(p);
		}
		return null;
	}

}
