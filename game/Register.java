package game;



import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.stage.Stage;


public class Register  {
	
	 VBox vbreg;
	 HBox hbreg1,hbreg2,hbreg3,hbreg4,hbreg5,hbreg6;
	
	 Label registerreg,usernamereg,emailreg,passwordreg, conpassword, phonenum;
	
	 TextField tfusernamereg, tfemailreg, tfphonenum;
	 PasswordField pfpasswordreg, pfconpass;
	 
	 Button btnregisterreg;
	 
	 	Menu mr;
		MenuBar mbar;
		MenuItem m01, m02;
		
		BorderPane bp;
	public void initialize() {
	
		mr = new Menu("Menu");
    	
    	m01 = new MenuItem("Login");
    	m02 = new MenuItem("Register");
    	 m01.setOnAction(event -> {
        
             openLoginPage();
         });
    	
    	mr.getItems().add(m01);
    	mr.getItems().add(m02);
    	
    	mbar = new MenuBar();
  
    	mbar.getMenus().add(mr);

	        
		registerreg = new Label("REGISTER");
		registerreg.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		
		 usernamereg = new Label("username");
		 emailreg = new Label("email");
		 passwordreg = new Label("password");
		 conpassword = new Label("Confirm Password");
		 phonenum = new Label("Phone Number");
		
		
		 btnregisterreg = new Button("Sign Up");
		
		
		 tfusernamereg = new TextField();
		 tfusernamereg.setMaxWidth(200);
		 
		 tfemailreg = new TextField();
		 tfemailreg.setMaxWidth(200);
	
		 pfpasswordreg = new PasswordField();
		 pfpasswordreg.setMaxWidth(200);
		 
		 pfconpass = new PasswordField();
		 pfconpass.setMaxWidth(200);
		 
		 tfphonenum = new TextField();
		 tfphonenum.setMaxWidth(200);

		hbreg1 = new HBox();
		hbreg2 = new HBox();		
		hbreg3 = new HBox();
		hbreg4 = new HBox();
		hbreg5 = new HBox();
		
		hbreg1.getChildren().add(usernamereg);
		hbreg2.getChildren().add(emailreg);
		hbreg3.getChildren().add(passwordreg);
		hbreg4.getChildren().add(conpassword);
		hbreg5.getChildren().add(phonenum);
		vbreg = new VBox(5);
		
		vbreg.getChildren().addAll(mbar, registerreg, hbreg1, tfusernamereg, hbreg2, tfemailreg, hbreg3, pfpasswordreg, hbreg4, pfconpass, hbreg5, tfphonenum, btnregisterreg);
		vbreg.setAlignment(Pos.CENTER);
	    vbreg.setPadding(new Insets(50, 50, 50, 50));
	    
	    btnregisterreg.setOnAction(e -> {           
	    	if (isValidInput()) {
                registerUser();
            } else {
                
            }
        });
    }

	
	private boolean isValidInput() {
	    String username = tfusernamereg.getText();
	    if (username.isEmpty() || username.length() < 4 || username.length() > 20) {
	        Alert invalidAlert = new Alert(Alert.AlertType.WARNING);
	        invalidAlert.setHeaderText("Username is invalid");
	        invalidAlert.setContentText("Username must contain 4 – 20 characters");
	        invalidAlert.showAndWait();
	        return false;
	    }

	    String email = tfemailreg.getText();
	    if (email.isEmpty() || !email.contains("@")) {
	        Alert invalidAlert = new Alert(Alert.AlertType.WARNING);
	        invalidAlert.setHeaderText("Email is invalid");
	        invalidAlert.setContentText("Email must contains “@” in it");
	        invalidAlert.showAndWait();
	        return false;
	    }

	    String password = pfpasswordreg.getText();
	    if (password.isEmpty() || password.length() < 6 || password.length() > 20 || !password.matches("^[a-zA-Z0-9]+$")) {
	        Alert invalidAlert = new Alert(Alert.AlertType.WARNING);
	        invalidAlert.setHeaderText("Password is invalid");
	        invalidAlert.setContentText("Password must contain 6 – 20 characters");
	        invalidAlert.showAndWait();
	        return false;
	    }

	    String confirmPassword = pfconpass.getText();
	    if (!confirmPassword.equals(password)) {
	        Alert invalidAlert = new Alert(Alert.AlertType.WARNING);
	        invalidAlert.setHeaderText("Password is invalid");
	        invalidAlert.setContentText("Confirm Password must be the same as Password");
	        invalidAlert.showAndWait();
	        return false;
	    }

	    String phoneNumber = tfphonenum.getText();
	    if (!phoneNumber.matches("\\d{9,20}")) {
	        Alert invalidAlert = new Alert(Alert.AlertType.WARNING);
	        invalidAlert.setHeaderText("Phone Number is invalid");
	        invalidAlert.setContentText("Phone Number must be a numeric value with 9-20 digits");
	        invalidAlert.showAndWait();
	        return false;
	    }

	    // If all conditions pass, return true
	    return true;
	}

	private String generateUserID() {
	    Connect connect = Connect.getInstance();
	    String query = "SELECT userId FROM user ORDER BY userId DESC LIMIT 1";
	    try {
	        ResultSet resultSet = connect.execQuery(query);
	        if (resultSet.next()) {
	            String lastUserID = resultSet.getString("userId");
	            int lastNumber = Integer.parseInt(lastUserID.substring(2));
	            int nextNumber = lastNumber + 1;
	            String formattedNumber = String.format("%03d", nextNumber);
	            return "AC" + formattedNumber;
	        } else {
	            return "AC001"; 
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
    
	public void registerUser() {
		Connect connect = Connect.getInstance();
		String userId = generateUserID(); 
		String username = tfusernamereg.getText();
		String password = pfpasswordreg.getText();
		String phonenum = tfphonenum.getText();
		String email = tfemailreg.getText();
		String role = "customer";
		
		User newUser = new User(userId, username, phonenum, password, email, role);
		connect.insertUserData(newUser.getUserId(), newUser.getUsername(), newUser.getPassword(), newUser.getPhoneNumber(), newUser.getEmail(), newUser.getRole());

		 Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
		    successAlert.setHeaderText("Registration Successful");
		    successAlert.setContentText("You have successfully registered!");
		    successAlert.showAndWait();

		    openLoginPage();
	}
	
    private void openLoginPage() {
		// TODO Auto-generated method stub
    	  Stage currentStage = (Stage) vbreg.getScene().getWindow(); 
     	 currentStage.close(); 
    	 Login loginpage = new Login();
    	 loginpage.initialize();
         Scene scene = new Scene(loginpage.getRoot(), 300, 250);
         Stage stage = new Stage();
         stage.setScene(scene);
         stage.setTitle("SNeam");
         stage.show();
	}

	public VBox getRoot() {
        return vbreg;
    }
	

}

