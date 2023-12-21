package game;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Login implements EventHandler<ActionEvent> {
        User user;
	Connect connect;
	Label nameLbl,passwordLbl, loginLbl;
	HBox hboxemail, hboxpass;
	
	
	VBox vbox;
	Menu m;
	MenuBar mb;
	MenuItem m1, m2;
	BorderPane bp;

    public void initialize() {
   

    	m = new Menu("Menu");
    	
    	m1 = new MenuItem("Login");
    	m2 = new MenuItem("Register");
    	 m2.setOnAction(event -> {
             openRegisterPage();
         });
    	
    	m.getItems().add(m1);
    	m.getItems().add(m2);
    	
    	mb = new MenuBar();
  
    	mb.getMenus().add(m);
    	
    	loginLbl = new Label("LOGIN");
    	loginLbl.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        TextField email = new TextField();
        email.setMaxWidth(200);
        nameLbl = new Label("Email");
        PasswordField password = new PasswordField();
        password.setMaxWidth(200);
        passwordLbl = new Label("Password");
        
        Button registerButton = new Button("Sign In");
     
        registerButton.setOnAction(event -> {
            String enteredEmail = email.getText();
            String enteredPassword = password.getText();

            Connect connect = new Connect();
            if (validateUser(enteredEmail, enteredPassword)) {
                showAlert("Login Successful", "Welcome, " + this.user.getUsername() + "!");
            try {
                if(user.getRole().equals("admin")){
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("/game/Admin.fxml"));
               System.out.println(loader.getLocation()); // Add this line 
               Stage listOrder = new Stage();
                listOrder.initModality(Modality.APPLICATION_MODAL);
                listOrder.setScene(new Scene(loader.load()));
                AdminController produkController = loader.getController();
                produkController.setUser(user);
                listOrder.showAndWait();
                }else{
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/game/Home.fxml"));
               System.out.println(loader.getLocation()); // Add this line 
               Stage listOrder = new Stage();
                listOrder.initModality(Modality.APPLICATION_MODAL);
                listOrder.setScene(new Scene(loader.load()));
                HomeController produkController = loader.getController();
                produkController.setUser(user);
                listOrder.showAndWait();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
                
            } else {
                showAlert("Login Failed", "Invalid email or password. Please try again.");
            }
        });

        Label loginError = new Label();
        loginError.setStyle("-fx-text-fill: red;");
        
       
        hboxemail = new HBox();
        hboxemail.getChildren().add(nameLbl);

        hboxpass = new HBox();
        hboxpass.getChildren().add(passwordLbl);

        vbox = new VBox(5);
        vbox.setPadding(new Insets(0,50,50,50));
        vbox.getChildren().addAll(mb, loginLbl, hboxemail, email, hboxpass, password, registerButton, loginError);
        vbox.setAlignment(Pos.CENTER);
    }
    private void openRegisterPage() {
       
       
    	  Stage currentStage = (Stage) vbox.getScene().getWindow(); 
    	 currentStage.close(); 
    	 Register register = new Register();
    	 register.initialize();
         Scene scene = new Scene(register.getRoot(), 300, 400);
         Stage stage = new Stage();
         stage.setScene(scene);
         stage.setTitle("SNeam");
         stage.show();
         
	}

    public boolean validateUser(String email, String password) {
    	connect = new Connect();
        String query = "SELECT * FROM user WHERE email = '" + email + "' AND password = '" + password + "'";
        ResultSet resultSet = connect.execQuery(query);

         try {
            if (resultSet.next()) {
                user = new User(
                        resultSet.getString("UserId"),
                        resultSet.getString("Username"),
                        resultSet.getString("PhoneNumber"),
                        resultSet.getString("Password"),
                        resultSet.getString("Email"),
                        resultSet.getString("Role")
                );
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
         return false;
    }
       	
    public void openHome(String userId) {
		// TODO Auto-generated method stub
    	Stage currentStage = (Stage) vbox.getScene().getWindow();
        currentStage.close();
        
        Home home = new Home();
        home.setUserId(userId); 
        home.getData();
        home.inittable();
        VBox homeLayout = home.getRoot();
        Scene scene = new Scene(homeLayout, 900, 600);
      
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("SNeam");
        stage.show();
         
	}
    
    public void showAlert(String header, String content) {
        Alert LoginAlert = new Alert(Alert.AlertType.WARNING);
        LoginAlert.setHeaderText(header);
        LoginAlert.setContentText(content);
        LoginAlert.showAndWait();
    }
    

    public VBox getRoot() {
        return vbox;
    }
	@Override
	public void handle(ActionEvent event) {
		
 	 
 }
		
	}
    