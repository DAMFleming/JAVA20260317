package fp.dam.java;

import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.groupingBy;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

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
	
	static Map<String, Double> metodo1() {
		return products.stream().collect(groupingBy(
				Product::getProductLine,
				TreeMap::new,
				averagingDouble(Product::getMsrp)));
	}
	
	static double metodo2() {
		return orders.stream()
			.mapToDouble(p -> orderDetails.stream()
				.filter(od -> od.getOrderNumber() == p.getOrderNumber())
				.collect(Collectors.summingDouble(od -> od.getQuantityOrdered() * od.getPriceEach())))
			.average().getAsDouble();
	}
	
	static String metodo3() {
		return products.stream()
				.collect(groupingBy(Product::getVendor, Collectors.counting()))
				.entrySet()
				.stream()
				.max(Comparator.comparingLong(e -> e.getValue()))
				.get()
				.getKey();
	}
	
	
	
	public static void main(String[] args) {
//		metodo1().entrySet().forEach(System.out::println);
		System.out.println(metodo2());
		System.out.println(metodo3());
	}
	
}
