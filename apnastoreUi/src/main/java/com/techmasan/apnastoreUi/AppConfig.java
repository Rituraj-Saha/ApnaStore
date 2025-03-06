package com.techmasan.apnastoreUi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import entity.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


@Configuration
public class AppConfig {
	
	@Bean
	public ObservableList<Product> productList() {
		ObservableList<Product> products = FXCollections.observableArrayList(
		         new Product(1L,"Test Product",150.0,1.5),
		         new Product(2L,"Test Product2",150.0,1.5)
		      );
        return products;
    }
}
