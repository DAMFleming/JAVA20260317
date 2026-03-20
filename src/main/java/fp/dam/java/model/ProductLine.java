package fp.dam.java.model;

import java.util.Comparator;
import java.util.Objects;

import org.apache.commons.csv.CSVRecord;

public class ProductLine implements Comparable<ProductLine> {

	private String productLine;
	private String textDescription;
	
	public ProductLine(CSVRecord r) {
		productLine = r.get("productLine");
		textDescription = r.get("textDescription");
	}

	public String getProductLine() {
		return productLine;
	}

	public String getTextDescription() {
		return textDescription;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(productLine, textDescription);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductLine other = (ProductLine) obj;
		return Objects.equals(productLine, other.productLine) && Objects.equals(textDescription, other.textDescription);
	}

	@Override
	public String toString() {
		return "ProductLine [productLine=" + productLine + ", textDescription=" + textDescription + "]";
	}

	private static Comparator<ProductLine> c = Comparator.comparing(ProductLine::getProductLine).thenComparing(ProductLine::getTextDescription);
	@Override
	public int compareTo(ProductLine pl) {
		return c.compare(this, pl);
	}

}
