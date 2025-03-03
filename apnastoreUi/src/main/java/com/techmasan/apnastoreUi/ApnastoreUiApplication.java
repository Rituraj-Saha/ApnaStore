package com.techmasan.apnastoreUi;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.stage.Stage;

public class ApnastoreUiApplication extends Application {

	private ConfigurableApplicationContext context;

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		super.init();
		ApplicationContextInitializer<GenericApplicationContext> initializer = ac -> {
			ac.registerBean(Application.class, () -> ApnastoreUiApplication.this);
			ac.registerBean(Parameters.class, this::getParameters);
			ac.registerBean(HostServices.class, this::getHostServices);
		};
		this.context = new SpringApplicationBuilder().sources(JavaFxStarter.class).initializers(initializer)
				.run(getParameters().getRaw().toArray(new String[0]));
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		this.context.publishEvent(new StageReadyEvent(primaryStage));
	}

	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		super.stop();
		this.context.close();
		Platform.exit();
	}
}
class StageReadyEvent extends ApplicationEvent{

	public Stage getStage() {
		return Stage.class.cast(getSource());
	}
	public StageReadyEvent(Stage source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
	
}
