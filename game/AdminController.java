package game;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdminController {
    
     public User user;
    public void setUser(User user){
        
        this.user = user;
        userLabel.setText("Hello ,"+user.getUsername());
         initializeGameList();
    }
    
    
    @FXML
    private Button addBtn;

    @FXML
    private Button addCart;

    @FXML
    private Pane dashboard;

    @FXML
    private Button deleteBtn;

    @FXML
    private TextArea descField;

    @FXML
    private Label description;

    @FXML
    private Pane detailPane;

    @FXML
    private AnchorPane dkk;

    @FXML
    private Label harga;

    @FXML
    private ListView<Game> listGame;

    @FXML
    private MenuItem logout;

    @FXML
    private Label nama;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private Button updateBtn;

    @FXML
    private Label userLabel;
   
    public Game selected;

  
   
    
    @FXML
private void initialize() {
   
    GameModel gameModel = new GameModel();

    updateBtn.setOnAction(event -> {
        if (!isAnyFieldEmpty()) {
        String nama = nameField.getText();
        String desc = descField.getText();
        String price = priceField.getText();

        // Update the selected game
        selected.setDescription(desc);
        selected.setNama(nama);
        selected.setPrice(price);

        // Update the game in the database
        gameModel.updateGame(selected);

        // Refresh the ListView by updating the item directly
        int selectedIndex = listGame.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            listGame.getItems().set(selectedIndex, selected);
        }

        // Clear input fields
        clearField();
        }
    });

    deleteBtn.setOnAction(event -> {
    // Show a confirmation dialog before deleting
    Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
    confirmationAlert.setHeaderText("Are you sure you want to delete this game?");
    confirmationAlert.setContentText("");

    // Retrieve the user's response
    ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.CANCEL);

    // If the user confirms the deletion, proceed with the deletion
    if (result == ButtonType.OK) {
        // Delete the selected game
        gameModel.deleteGame(selected.getId());

        // Remove the selected game from the ListView
        listGame.getItems().remove(selected);

        // Clear input fields
        clearField();
    }
});

    addBtn.setOnAction(event -> {
        if (!isAnyFieldEmpty()) {
        String nama = nameField.getText();
        String desc = descField.getText();
        String price = priceField.getText();

        // Create a new game
        Game newGame = new Game(nama, desc, price);

        // Add the new game to the database
        String id = gameModel.createGame(newGame);
        newGame.setId(id);
        // Add the new game to the ListView
        listGame.getItems().add(newGame);

        // Clear input fields
        clearField();
        }
    });

    // Initialize the ListView
    listGame.setItems(GameData.getGames());

    // Handle selection changes in the ListView
    listGame.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
            selected = newValue;
            showGameDetails(newValue);
        }
    });
}
     private void initializeGameList() {
         System.out.println("initialize");
        listGame.setItems(GameData.getGames());
        listGame.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selected = newValue;
                showGameDetails(newValue);
            }
        });
       
       
         
    }
  
    public void clearField(){
        nameField.setText("");
        descField.setText("");
        priceField.setText("");
    }
    private void showGameDetails(Game selectedGame) {
 
      
        nameField.setText(selectedGame.getNama());
        descField.setText(selectedGame.getDescription());
        priceField.setText(selectedGame.getPrice());
        
        
    }
      public void showAlert(String header, String content) {
        Alert LoginAlert = new Alert(Alert.AlertType.WARNING);
        LoginAlert.setHeaderText(header);
        LoginAlert.setContentText(content);
        LoginAlert.showAndWait();
    }
    @FXML
    void LogOut(ActionEvent event) {
         
            System.out.println("1");
        ((Stage) dkk.getScene().getWindow()).close();
       
    }

    private boolean isAnyFieldEmpty() {
    String namas = nameField.getText();
    String desc = descField.getText();
    String priceText = priceField.getText();

    if (namas.isEmpty() || desc.isEmpty() || priceText.isEmpty()) {
        String emptyField = "";

        if (namas.isEmpty()) {
            emptyField = "game name";
        } else if (desc.isEmpty()) {
            emptyField = "game description";
        } else if (priceText.isEmpty()) {
            emptyField = "game price";
        }

        showAlert("You haven't inputted a " + emptyField ,  "Please fill the " + emptyField + " form.");
        return true;
    }

    // Check if the price is too high
    try {
        long price = Long.parseLong(priceText);
        if (price > 9999999999L) {
            showAlert("Price cannot exceed 9999999999.", " Price Too High");
            return true;
        }
    } catch (NumberFormatException e) {
        showAlert("Invalid Price", "Please enter a valid numeric price.");
        return true;
    }

    return false;
}


}
