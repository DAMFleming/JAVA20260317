package fp.dam.java.model;

import java.util.Objects;

import org.apache.commons.csv.CSVRecord;

public class OrderDetail {
	private int orderNumber;
	private String productCode;
	private int quantityOrdered;
	private float priceEach;
	private int orderLineNumber;
	
	public OrderDetail(CSVRecord r) {
		orderNumber = Integer.parseInt(r.get("orderNumber"));
		productCode = r.get("productCode");
		quantityOrdered = Integer.parseInt(r.get("quantityOrdered"));
		priceEach = Float.parseFloat(r.get("priceEach"));
		orderLineNumber = Integer.parseInt(r.get("orderLineNumber"));	
	}
	
	public int getOrderNumber() {
		return orderNumber;
	}

	public String getProductCode() {
		return productCode;
	}

	public int getQuantityOrdered() {
		return quantityOrdered;
	}

	public float getPriceEach() {
		return priceEach;
	}

	public int getOrderLineNumber() {
		return orderLineNumber;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(orderLineNumber, orderNumber, priceEach, productCode, quantityOrdered);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetail other = (OrderDetail) obj;
		return orderLineNumber == other.orderLineNumber && orderNumber == other.orderNumber
				&& Float.floatToIntBits(priceEach) == Float.floatToIntBits(other.priceEach)
				&& Objects.equals(productCode, other.productCode) && quantityOrdered == other.quantityOrdered;
	}

	@Override
	public String toString() {
		return "OrderDetail [orderNumber=" + orderNumber + ", productCode=" + productCode + ", quantityOrdered="
				+ quantityOrdered + ", priceEach=" + priceEach + ", orderLineNumber=" + orderLineNumber + "]";
	}
	
}