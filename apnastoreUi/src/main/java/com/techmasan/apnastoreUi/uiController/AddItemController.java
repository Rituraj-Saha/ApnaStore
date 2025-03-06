package com.techmasan.apnastoreUi.uiController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techmasan.apnastoreUi.service.ProductService;

import entity.Product;
import jakarta.annotation.PostConstruct;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;



@Component
public class AddItemController {

	@Autowired
	ProductService ps;
	@FXML
    public TableView<Product> mtable;

    @FXML
    public void initialize() {
        // Create columns dynamically (if not defined in FXML)
        TableColumn<Product, Long> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Add columns to table
        mtable.getColumns().addAll(idColumn, nameColumn, priceColumn);

        // Populate data
        loadProducts();
    }
    @FXML
    private void loadProducts() {
        // Sample products (you can fetch from a database or service)
//        productList.add(new Product(1L, "Laptop", 1500.00));
//        productList.add(new Product(2L, "Smartphone", 700.00));
//        productList.add(new Product(3L, "Headphones", 150.00));
    	if(ps!=null)
        mtable.setItems(ps.getAllProducts());
    	else
    		System.out.println("PS not autowird");
    }
}
