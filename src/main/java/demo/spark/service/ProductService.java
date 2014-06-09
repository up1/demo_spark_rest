package demo.spark.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import demo.spark.model.Product;

public class ProductService {
	
	private Map<String, Product> products = new HashMap<String, Product>();
	
	public List<Product> getAllProduct() {
		return new ArrayList<Product>(products.values());
	}
	
	public Product getProduct(String productId) {
		return products.get(productId);
	}
	
	public Product createNewProduct( String name, Double price ) {
		Product product = new Product(name, price);
		products.put(product.getProductId(), product);
		return product;
	}

}
