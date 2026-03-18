package fp.dam.java;

import java.util.TreeSet;

import fp.dam.java.model.Product;

public class Ejercicio02 {

	public static void main(String[] args) {
		TreeSet<Product> products = new TreeSet<>(Ejercicio03.products);
		products.forEach(p -> System.out.println(p.getProductLine() + ", " + p.getName()));
	}

}
