package fp.dam.java.model;

import java.util.Objects;

import org.apache.commons.csv.CSVRecord;

public class ProductLine {

	private String productLine;
	private String textDescription;
	
	public ProductLine(String csvLine) {
		String [] tokens = csvLine.split("\\|");
		productLine = tokens[0];
		textDescription = tokens[1];
	}
	
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

}
