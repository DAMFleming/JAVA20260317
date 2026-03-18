package fp.dam.java;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;

import fp.dam.java.model.Order;
import fp.dam.java.model.OrderDetail;
import fp.dam.java.model.Product;
import fp.dam.java.model.ProductLine;

public class Ejercicio03 {
	
	public static final List<Product> products;
	public static final List<ProductLine> productLines;
	public static final List<Order> orders;
	public static final List<OrderDetail> orderDetails;
	
	static {
		
		try (Reader in = new FileReader(Ejercicio03.class.getResource("/csv/products.csv").getFile())) {
			products = new LinkedList<>();
			CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).get().parse(in).forEach(r -> products.add(new Product(r)));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		try (Reader in = new FileReader(Ejercicio03.class.getResource("/csv/productlines.csv").getFile())) {
			productLines = new LinkedList<>();
			CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).get().parse(in).forEach(r -> productLines.add(new ProductLine(r)));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		try (Reader in = new FileReader(Ejercicio03.class.getResource("/csv/orders.csv").getFile())) {
			orders = new LinkedList<>();
			CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).get().parse(in).forEach(r -> orders.add(new Order(r)));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		try (Reader in = new FileReader(Ejercicio03.class.getResource("/csv/orderdetails.csv").getFile())) {
			orderDetails = new LinkedList<>();
			CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).get().parse(in).forEach(r -> orderDetails.add(new OrderDetail(r)));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public static void main(String[] args) {
//		products.forEach(System.out::println);
//		productLines.forEach(System.out::println);
//		orders.forEach(System.out::println);
//		orderDetails.forEach(System.out::println);
	}
	
}
