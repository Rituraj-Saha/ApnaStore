package entity;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {
	 private SimpleLongProperty id;
	 private SimpleStringProperty name;
	 private SimpleDoubleProperty price;
	 private SimpleDoubleProperty stock;
	public Product(long l, String string, double d,
			double e) {
		super();
		this.id = new SimpleLongProperty(l);
		this.name =new SimpleStringProperty(string);
		this.price = new SimpleDoubleProperty(d);
		this.stock = new SimpleDoubleProperty(e);
	}
	public Product(String name, Double price, Double stock) {
		super();
		this.name = new SimpleStringProperty(name);
		this.price = new SimpleDoubleProperty(price);
		this.stock = new SimpleDoubleProperty(stock);
	}
	public Product() {
		super();
	}
	public SimpleLongProperty getId() {
		return id;
	}
	public void setId(SimpleLongProperty id) {
		this.id = id;
	}
	public SimpleStringProperty getName() {
		return name;
	}
	public void setName(SimpleStringProperty name) {
		this.name = name;
	}
	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}
	public SimpleDoubleProperty getPrice() {
		return price;
	}
	public void setPrice(SimpleDoubleProperty price) {
		this.price = price;
	}
	public void setPrice(Double price) {
		this.price = new SimpleDoubleProperty(price);
	}
	public SimpleDoubleProperty getStock() {
		return stock;
	}
	public void setStock(SimpleDoubleProperty stock) {
		this.stock = stock;
	}
	public void setStock(Double stock) {
		this.stock = new SimpleDoubleProperty(stock);
	}
	 
}
