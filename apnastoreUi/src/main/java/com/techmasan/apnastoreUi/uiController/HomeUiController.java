package com.techmasan.apnastoreUi.uiController;

import java.awt.event.ActionEvent;

import org.springframework.stereotype.Component;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

@Component
public class HomeUiController {

	private final HostServices hostServices;

	@FXML
	public Label lable;

	@FXML
	public Button button;

	public HomeUiController(HostServices hostServices) {
		// TODO Auto-generated constructor stub
		this.hostServices = hostServices;
	}

	@FXML
	public void initialize() {
		this.button.setOnAction(actionEvent -> this.lable.setText(this.hostServices.getDocumentBase()));
	}

}
