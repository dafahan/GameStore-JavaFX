package game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

	public class Main extends Application {

	    public static void main(String[] args) {
	        launch(args);
	    }

	    @Override
	    public void start(Stage primaryStage) {
	        Login loginpage = new Login();
	        loginpage.initialize();
	        Scene scene = new Scene(loginpage.getRoot(), 300, 250);
	        primaryStage.setTitle("SNeam");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }
	}



