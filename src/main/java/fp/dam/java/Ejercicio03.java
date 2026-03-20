package fp.dam.java;

import static java.util.stream.Collectors.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import static java.util.stream.Collectors.*;

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
	
	public static class ProductOrderDetail {
		private Product product;
		private OrderDetail orderDetail;
		public ProductOrderDetail(Product product, OrderDetail orderDetail) {
			this.product = product;
			this.orderDetail = orderDetail;
		}
		public Product getProduct() {
			return product;
		}
		public OrderDetail getOrderDetail() {
			return orderDetail;
		}
	}
	
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
	
	/*
	 * Método que retorne la media de los precios de venta recomendados de cada línea de
	 * producto.
	 */
	static Map<String, Double> metodo1() {
		return products.stream().collect(groupingBy(
				Product::getProductLine,
				TreeMap::new,
				averagingDouble(Product::getMsrp)));
	}
	
	/*
	 * Método que retorne la media de los importes totales de todos los pedidos.
	 */
	static double metodo2() {
		return orders.stream()
			.mapToDouble(p -> orderDetails.stream()
				.filter(od -> od.getOrderNumber() == p.getOrderNumber())
				.collect(summingDouble(od -> od.getQuantityOrdered() * od.getPriceEach())))
			.average().getAsDouble();
	}
	
	
	/*
	 * Método que retorne el proveedor (vendor) que suministra la mayor cantidad de productos.
	 */
	static String metodo3() {
		return products.stream()
				.collect(groupingBy(Product::getVendor, counting()))
				.entrySet()
				.stream()
				.max(Comparator.comparingLong(e -> e.getValue()))
				.get()
				.getKey();
	}
	
	/*
	 * Método que retorne el importe total que han generado las ventas de productos de cada
	 * proveedor.
	 */
	public static Map<String, Double> metodo4() {
		return orderDetails.stream()
				.collect(
						groupingBy(
								od -> products.stream().filter(p -> p.getCode().equals(od.getProductCode())).findFirst().get().getVendor(),
								summingDouble(od -> od.getPriceEach() * od.getQuantityOrdered())));
	}
	
	/*
	 * Método que retorne una LinkedList que contenga todas las líneas de detalle en las que la línea
	 * de producto del producto pedido sea “Trains” o “Ships”, ordenadas por número de proveedor como primer
	 * criterio de ordenación y por cantidad pedida (quantity ordered) como segundo criterio de ordenación
	 */
	
	private static Set<String> set = Set.of("Trains", "Ships");
	public static List<OrderDetail> metodo5() {
		return orderDetails.stream()
				.filter(od -> set.contains(products.stream()
						.filter(p -> p.getCode().equals(od.getProductCode()))
						.findFirst()
						.get()
						.getProductLine()))
				.sorted(Comparator.comparing(OrderDetail::getOrderNumber).thenComparing(OrderDetail::getQuantityOrdered))
				.collect(toCollection(LinkedList::new));
	}
	
	/*
	 * Método que retorne una lista que contenga el resultado de efectuar una operación equivalente a
	 * la operación join del lenguaje SQL entre la lista products y la lista orderDetails.
	 * Los elementos de la lista retornada serán instancias de una clase que tendrás que definir.
	 */
	public static List<ProductOrderDetail> metodo6() {
		return products.stream()
				.flatMap(p -> orderDetails.stream()
									.filter(od -> od.getProductCode().equals(p.getCode()))
									.map(od -> new ProductOrderDetail(p, od)))
				.toList();
	}
	
	public static void main(String[] args) {
//		metodo1().entrySet().forEach(System.out::println);
//		System.out.println(metodo2());
//		System.out.println(metodo3());
//		metodo4().forEach((k, v) -> System.out.println(k + ": " + v));
//		metodo5().forEach(System.out::println);
		metodo6().forEach(od -> System.out.println(
				od.getProduct().getName() + " - " +
				od.getProduct().getCode() + " - " +
				od.getOrderDetail().getProductCode() + " - " +
				(od.getOrderDetail().getQuantityOrdered() * od.getOrderDetail().getPriceEach() )));
	}
	
}
