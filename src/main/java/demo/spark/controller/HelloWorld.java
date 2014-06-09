package demo.spark.controller;

import static spark.Spark.*;
import spark.Request;
import spark.Response;
import spark.Route;

public class HelloWorld {
	public static void main(String[] args) {
		get("/hello", new Route() {
			@Override
			public Object handle(Request arg0, Response arg1) {
				return "Hello World";
			}
		});
	}
}
