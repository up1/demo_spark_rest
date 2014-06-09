package demo.spark.controller;

import static spark.Spark.after;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static demo.spark.util.JsonUtil.*
import spark.ExceptionHandler;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Route;
import demo.spark.model.Product;
import demo.spark.service.ProductService;
import demo.spark.util.JsonUtil;

public class ProductController {

	public ProductController(final ProductService productService) {
		
		get("/products", (request, response) -> productService.getAllProduct(), json());
		
		get("/product/:id", (request, response) -> {
			String id = request.params(":id");
			return productService.getProduct(id);
		}, json());
		
		post("/product", (req, res) -> productService.createNewProduct(
				req.queryParams("name"),
				Double.valueOf(req.queryParams("price"))
		), json());
		
		get("/products", new Route() {
			public Object handle(Request request, Response response) {
				return JsonUtil.toJson(productService.getAllProduct());
			}
		});
		get("/product/:id", new Route() {
			public Object handle(Request request, Response response) {
				Product product = productService.getProduct(request
						.params(":id"));
				return JsonUtil.toJson(product);
			}
		});

		post("/product", new Route() {
			public Object handle(Request request, Response response) {
				Product product = productService.createNewProduct(
						request.queryParams("name"),
						Double.valueOf(request.queryParams("price")));
				return JsonUtil.toJson(product);
			}
		});

		after(new Filter() {
			@Override
			public void handle(Request request, Response response)
					throws Exception {
				response.type("application/json");
			}
		});
		
		exception(IllegalArgumentException.class, new ExceptionHandler() {
			
			@Override
			public void handle(Exception exception, Request request, Response response) {
				response.status(400);
				response.body(JsonUtil.toJson(new ResponseError(exception)));
			}
		});

	}

}
