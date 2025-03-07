package com.techmasan.apnastoreUi.uiController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techmasan.apnastoreUi.service.ProductService;

import entity.Product;
import jakarta.annotation.PostConstruct;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

@Component
public class AddItemController {

	@Autowired
	ProductService ps;
	@FXML
	public TableView<Product> mtable;
	@FXML
	private Button plusButton;

	@FXML
	public void initialize() {
		// Create columns dynamically (if not defined in FXML)
		TableColumn<Product, Long> idColumn = new TableColumn<>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//		nameColumn.setOnEditCommit((TableColumn.CellEditEvent<Product, String> t) -> (t.getTableView().getItems()
//				.get(t.getTablePosition().getRow())).setName(new SimpleStringProperty(t.getNewValue())));
		nameColumn.setOnEditCommit(event -> {
			Product product = event.getRowValue();
		    product.setName(event.getNewValue());
		});

		TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		priceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
//		priceColumn.setOnEditCommit((TableColumn.CellEditEvent<Product, Double> t) -> (t.getTableView().getItems()
//				.get(t.getTablePosition().getRow())).setPrice(new SimpleDoubleProperty(t.getNewValue())));
		priceColumn.setOnEditCommit(event -> {
			 Product product = event.getRowValue();
			 product.setPrice(event.getNewValue()); 
		});

		TableColumn<Product, Double> stockColumn = new TableColumn<>("Stock");
		stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
		stockColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
//		stockColumn.setOnEditCommit((TableColumn.CellEditEvent<Product, Double> t) -> (t.getTableView().getItems()
//				.get(t.getTablePosition().getRow())).setStock(new SimpleDoubleProperty(t.getNewValue())));
		stockColumn.setOnEditCommit(event -> {
			   Product product = event.getRowValue();
			    product.setStock(event.getNewValue());
		});

		idColumn.setCellValueFactory(cellData -> cellData.getValue().getId().asObject());
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
		priceColumn.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());
		stockColumn.setCellValueFactory(cellData -> cellData.getValue().getStock().asObject());
		
		
		TableColumn<Product, Void> deleteColumn = new TableColumn<>("Action");
        deleteColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    Product product = getTableView().getItems().get(getIndex());
                    getTableView().getItems().remove(product); // Remove from table
                });
                deleteButton.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });

		// Add columns to table
		mtable.getColumns().addAll(idColumn, nameColumn, priceColumn, stockColumn,deleteColumn);
		mtable.setEditable(true);
		 // Each row is 30 pixels tall

		double columnCount = mtable.getColumns().size();
		mtable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // Ensures table uses all available space
		idColumn.prefWidthProperty().bind(mtable.widthProperty().divide(columnCount));
		nameColumn.prefWidthProperty().bind(mtable.widthProperty().divide(columnCount));
		priceColumn.prefWidthProperty().bind(mtable.widthProperty().divide(columnCount));
		stockColumn.prefWidthProperty().bind(mtable.widthProperty().divide(columnCount));
		// Populate data
		loadProducts();
//		mtable.setFixedCellSize(60);
		mtable.prefHeightProperty().bind(Bindings.size(mtable.getItems()).multiply(30).add(30));
		
		plusButton.setOnAction(this::showPopupForm);
	}

	@FXML
	private void loadProducts() {
		// Sample products (you can fetch from a database or service)
//        productList.add(new Product(1L, "Laptop", 1500.00));
//        productList.add(new Product(2L, "Smartphone", 700.00));
//        productList.add(new Product(3L, "Headphones", 150.00));

		if (ps != null)
			mtable.setItems(ps.getAllProducts());
		else
			System.out.println("PS not autowird");
	}

	private void showPopupForm(ActionEvent event) {
		// Create Popup Stage
		Stage popupStage = new Stage();
		popupStage.initModality(Modality.APPLICATION_MODAL); // Block background window
		popupStage.setTitle("Add New Item");

		// Form Elements
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);

		Label nameLabel = new Label("Item Name:");
		TextField nameField = new TextField();

		Label priceLabel = new Label("Price:");
		TextField priceField = new TextField();

		Label stockLabel = new Label("Stock:");
		TextField stockField = new TextField();

		Button submitButton = new Button("Submit");
		submitButton.setOnAction(e -> {
			String name = nameField.getText();
			String price = priceField.getText();
			String stock = stockField.getText();
			ps.addProduct(new Product(name, Double.parseDouble(price),Double.parseDouble(stock)));

			popupStage.close(); // Close popup after submission
		});

		grid.add(nameLabel, 0, 0);
		grid.add(nameField, 1, 0);
		grid.add(priceLabel, 0, 1);
		grid.add(priceField, 1, 1);
		grid.add(stockLabel, 0, 2);
		grid.add(stockField, 1, 2);
		grid.add(submitButton, 2, 2);

		// Set Scene
		Scene scene = new Scene(grid, 300, 200);
		popupStage.setScene(scene);
		popupStage.showAndWait(); // Blocks interaction until popup is closed
	}

}
