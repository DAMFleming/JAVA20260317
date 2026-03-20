package fp.dam.java.model;

import java.util.Comparator;
import java.util.Objects;

import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class Product implements Comparable<Product> {
	
	private String code;
	private String name;
	private String productLine;
	private String scale;
	private String vendor;
	private String description;
	private int stock;
	private float buyPrice;
	private float msrp;

	public Product(CSVRecord r) {
		code = r.get("code");
		name = r.get("name");
		productLine = r.get("productLine");
		scale = r.get("scale");
		vendor = r.get("vendor");
		description = r.get("description");
		stock = Integer.parseInt(r.get("stock"));
		buyPrice = Float.parseFloat(r.get("buyPrice"));
		msrp = Float.parseFloat(r.get("msrp"));	
	}	
	
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getProductLine() {
		return productLine;
	}

	public String getScale() {
		return scale;
	}

	public String getVendor() {
		return vendor;
	}

	public String getDescription() {
		return description;
	}

	public int getStock() {
		return stock;
	}

	public float getBuyPrice() {
		return buyPrice;
	}

	public float getMsrp() {
		return msrp;
	}
	
	
	public void writeCSV(CSVPrinter printer) {
		
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(buyPrice, code, description, msrp, name, productLine, scale, stock, vendor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Float.floatToIntBits(buyPrice) == Float.floatToIntBits(other.buyPrice)
				&& Objects.equals(code, other.code) && Objects.equals(description, other.description)
				&& Float.floatToIntBits(msrp) == Float.floatToIntBits(other.msrp) && Objects.equals(name, other.name)
				&& Objects.equals(productLine, other.productLine) && Objects.equals(scale, other.scale)
				&& stock == other.stock && Objects.equals(vendor, other.vendor);
	}

	@Override
	public String toString() {
		return "Product [code=" + code + ", name=" + name + ", productLine=" + productLine + ", scale=" + scale
				+ ", vendor=" + vendor + ", description=" + description + ", stock=" + stock + ", buyPrice=" + buyPrice
				+ ", msrp=" + msrp + "]";
	}

	private static Comparator<Product> c = Comparator
			.comparing(Product::getProductLine)
			.thenComparing(Product::getVendor)
			.thenComparing(Product::getName)
			.thenComparing(Product::getScale)
			.thenComparing(Product::getDescription)
			.thenComparing(Product::getStock)
			.thenComparing(Product::getBuyPrice)
			.thenComparing(Product::getMsrp)
			.thenComparing(Product::getCode);
	
	@Override
	public int compareTo(Product p) {
		return c.compare(this, p);
	}
	
}