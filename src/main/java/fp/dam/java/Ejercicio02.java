package fp.dam.java;

import java.util.Set;
import java.util.TreeSet;

import fp.dam.java.model.Order;
import fp.dam.java.model.OrderDetail;
import fp.dam.java.model.Product;
import fp.dam.java.model.ProductLine;

public class Ejercicio02 {

	public static void main(String[] args) {
		Set<Product> products = new TreeSet<>(Ejercicio03.products);
		products.forEach(System.out::println);
		Set<ProductLine> productLines = new TreeSet<>(Ejercicio03.productLines);
		productLines.forEach(System.out::println);
		Set<Order> orders = new TreeSet<>(Ejercicio03.orders);
		orders.forEach(System.out::println);
		Set<OrderDetail> orderDetails = new TreeSet<>(Ejercicio03.orderDetails);
		orderDetails.forEach(System.out::println);
	}

}
