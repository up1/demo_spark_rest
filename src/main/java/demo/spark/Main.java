package demo.spark;

import demo.spark.controller.ProductController;
import demo.spark.service.ProductService;

public class Main {
	public static void main(String[] args) {
		new ProductController(new ProductService());
	}
}
