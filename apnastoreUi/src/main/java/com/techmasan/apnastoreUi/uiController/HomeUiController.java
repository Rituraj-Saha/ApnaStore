package com.techmasan.apnastoreUi.uiController;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

@Component
public class HomeUiController {

	private final HostServices hostServices;
	private final Resource fxml;
	private final ApplicationContext ac;
//	@FXML
//	public Label lable;
//
	@FXML
	public Button btnDashboard;
	
	@FXML
	public Button btnBiling;
	
	@FXML
	public Button btnAddItem;
	
	@FXML
	public AnchorPane frameAnchor;

	public HomeUiController(HostServices hostServices,@Value("classpath:/") Resource resource,ApplicationContext ac) {
		// TODO Auto-generated constructor stub
		this.hostServices = hostServices;
		this.fxml = resource;
		this.ac = ac;
	}

	@FXML
	public void initialize() {
//		this.button.setOnAction(actionEvent -> this.lable.setText(this.hostServices.getDocumentBase()));
//		this.btnDashboard.setOnAction(actionEvent -> System.out.println("dashboard clicked"));
//		this.btnBiling.setOnAction(actionEvent ->  System.out.println("billing clicked"));
//		this.btnAddItem.setOnAction(actionEvent -> System.out.println("add item clicked"));
		btnDashboard.setOnAction(actionEvent -> loadView("dashboard.fxml"));
        btnBiling.setOnAction(actionEvent -> loadView("billing.fxml"));
        btnAddItem.setOnAction(actionEvent -> loadView("addItem.fxml"));
	}
	
	  private void loadView(String fxmlFile) {
		  
	        try {
	        	URL url = new URL(this.fxml.getURL().toString()+fxmlFile);
	            FXMLLoader loader = new FXMLLoader(url); // Update path as per structure
	            loader.setControllerFactory(ac::getBean);
	            Parent view = loader.load();
	            frameAnchor.getChildren().setAll(view); // Replace content inside AnchorPane
	            AnchorPane.setTopAnchor(view, 0.0);
	            AnchorPane.setBottomAnchor(view, 0.0);
	            AnchorPane.setLeftAnchor(view, 0.0);
	            AnchorPane.setRightAnchor(view, 0.0);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

}
