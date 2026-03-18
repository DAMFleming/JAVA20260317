package fp.dam.java;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import fp.dam.java.model.Order;
import fp.dam.java.model.OrderDetail;
import fp.dam.java.model.Product;
import fp.dam.java.model.ProductLine;

public class WriteCSV {

	public static void main(String[] args) throws URISyntaxException, IOException {
		
		try (final CSVPrinter printer = new CSVPrinter(new FileWriter("src/main/resources/csv/products.csv"),
				CSVFormat.DEFAULT.builder().setHeader(Arrays.stream(Product.class.getDeclaredFields()).map(Field::getName).toArray(String[]::new)).get())) {
	        Ejercicio03.products.forEach(p -> {
	        	try {
					printer.printRecord(p.getCode(), p.getName(), p.getProductLine(), p.getScale(), p.getVendor(),
							p.getDescription(), p.getStock(), p.getBuyPrice(), p.getMsrp());
				} catch (IOException e) {
					e.printStackTrace();
				}
	        });
	    }
		
		try (final CSVPrinter printer = new CSVPrinter(new FileWriter("src/main/resources/csv/productlines.csv"),
				CSVFormat.DEFAULT.builder().setHeader(Arrays.stream(ProductLine.class.getDeclaredFields()).map(Field::getName).toArray(String[]::new)).get())) {
	        Ejercicio03.productLines.forEach(pl -> {
	        	try {
					printer.printRecord(pl.getProductLine(), pl.getTextDescription());
				} catch (IOException e) {
					e.printStackTrace();
				}
	        });
	    }
		
		try (final CSVPrinter printer = new CSVPrinter(new FileWriter("src/main/resources/csv/orders.csv"),
				CSVFormat.DEFAULT.builder().setHeader(Arrays.stream(Order.class.getDeclaredFields()).map(Field::getName).toArray(String[]::new)).get())) {
	        Ejercicio03.orders.forEach(o -> {
	        	try {
					printer.printRecord(o.getOrderNumber(), o.getOrderDate(), o.getRequiredDate(), o.getShippedDate(), o.getStatus(), o.getComments(), o.getCustomerNumber());
				} catch (IOException e) {
					e.printStackTrace();
				}
	        });
	    }
		
		try (final CSVPrinter printer = new CSVPrinter(new FileWriter("src/main/resources/csv/orderdetails.csv"),
				CSVFormat.DEFAULT.builder().setHeader(Arrays.stream(OrderDetail.class.getDeclaredFields()).map(Field::getName).toArray(String[]::new)).get())) {
	        Ejercicio03.orderDetails.forEach(od -> {
	        	try {
					printer.printRecord(od.getOrderNumber(), od.getProductCode(), od.getQuantityOrdered(), od.getPriceEach(), od.getOrderLineNumber());
				} catch (IOException e) {
					e.printStackTrace();
				}
	        });
	    }
		
	}

}
