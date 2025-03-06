package com.techmasan.apnastoreUi.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import entity.Product;
import javafx.beans.property.SimpleLongProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Service
public class ProductService {
	
	  private final ObservableList<Product> products;
	    private Long idCounter = 3L;
	
//	ObservableList<Product> initiateProduct(ArrayList<Product> products){
//		ObservableList<Product> initProducts = FXCollections.observableArrayList(products);
//		return initProducts;
//	}
	    public ProductService(ObservableList<Product> products) {
	        this.products = products;
	    }
	    public ObservableList<Product> getAllProducts() {
	        return products;
	    }
	    public Optional<Product> getProductById(Long id) {
	        return products.stream().filter(product -> product.getId().equals(id)).findFirst();
	    }
	    public Product addProduct(Product product) {
	        product.setId(new SimpleLongProperty(idCounter++));
	        products.add(product);
	        return product;
	    }
	    public void deleteProduct(Long id) {
	        products.removeIf(product -> product.getId().equals(id));
	    }

}