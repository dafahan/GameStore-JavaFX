package game;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HomeController {
    public User user;
    public void setUser(User user){
        this.user = user;
        userLabel.setText("Hello , "+user.getUsername());
         initializeGameList();
    }
   
    
    @FXML
    private Button addCart;

    @FXML
    private Pane cart;

    @FXML
    private Button checkout;

    @FXML
    private Pane dashboard;

    @FXML
    private MenuItem home;
    
      @FXML
    private MenuItem cartbtn;
    
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
    private TableColumn<Cart, Game> nameColumn;

    @FXML
    private TableColumn<Cart, Game> priceColumn;

    @FXML
    private TableColumn<Cart, Integer> quantityColumn;

    @FXML
    private TableView<Cart> tableCart;

    @FXML
    private Label total;
    
     @FXML
    private Label userLabel;
    @FXML
    private TableColumn<Cart, Cart> totalColumn;

    private Game selected;
    private ObservableList<Cart> orderList = FXCollections.observableArrayList();
    
    private void initializeGameList() {
        listGame.setItems(GameData.getGames());
        System.out.println(GameData.getGames());
        listGame.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selected = newValue;
                showGameDetails(newValue);
            }
        });
        CartModel cartModel = new CartModel();
        orderList = cartModel.getCart(user.getUserId());
          
         nameColumn.setCellValueFactory(new PropertyValueFactory<>("game"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("game"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("cart"));
      
        nameColumn.setCellFactory(col -> new TableCell<Cart, Game>() {
            @Override
            protected void updateItem(Game item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    // Access the game property from Cart
                    setText(item.getNama());
                }
            }
        });

        
        priceColumn.setCellFactory(col -> new TableCell<Cart, Game>() {
            @Override
            protected void updateItem(Game item, boolean empty) {
                super.updateItem(item, empty);

                if (empty||item==null) {
                    setText(null);
                } else {
                    
                    setText(item.getPrice());
                    
                }
            }
        });
        
        totalColumn.setCellFactory(col -> new TableCell<Cart,Cart >() {
            @Override
            protected void updateItem(Cart cart, boolean empty) {
                super.updateItem(cart, empty);

                if (empty || cart == null) {
                    setText(null);
                } else {
                    int total = cart.getQuantity() * Integer.parseInt(cart.getGame().getPrice());
                    setText(total+"");
                }
            }
        });

        System.out.println(orderList);
        tableCart.setItems(orderList);
       
         
    }
    @FXML
    private void initialize() {
      
       
        home.setOnAction(event -> {
            dashboard.setVisible(true);
            cart.setVisible(false);
        });
        
        cartbtn.setOnAction(event -> {
            dashboard.setVisible(false);
            cart.setVisible(true);
            int subtotal = orderList.stream().mapToInt(cart -> cart.getQuantity() * Integer.parseInt(cart.getGame().getPrice())).sum();
            total.setText("Gross Price: Rp. "+subtotal+"");
            
        });
        
         checkout.setOnAction(event -> {
             if (!orderList.isEmpty()) {
            TransactionModel transaction = new TransactionModel();
            transaction.addTransaction(user.getUserId(), orderList);
           CartModel cartModel = new CartModel();
           orderList = cartModel.getCart(user.getUserId());
           tableCart.setItems(orderList);
             }else{
                 showAlert("Cart Is Empty","Cart Is Empty");
             }
        });
        
        
    }

    private void showGameDetails(Game selectedGame) {
        TransactionModel model = new TransactionModel();
        System.out.println(model.generateUniqueTransactionID());
        // Show details in the detailPane when a game is selected
        detailPane.setVisible(true);
        nama.setText(selectedGame.getNama());
        description.setText(selectedGame.getDescription());
        harga.setText("RP. "+selectedGame.getPrice());
        
        addCart.setOnAction((ActionEvent event) -> {
             try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/game/addcart.fxml"));
                Stage listOrder = new Stage();
                listOrder.initModality(Modality.APPLICATION_MODAL);
                listOrder.setScene(new Scene(loader.load()));

                AddcartController produkController = loader.getController();
                orderList = produkController.setDetail(selectedGame,orderList,user); 
                 System.out.println(orderList);
                listOrder.showAndWait();
                CartModel cartModel = new CartModel();
                orderList = cartModel.getCart(user.getUserId());
                tableCart.setItems(orderList);

            } catch (IOException e) {
                e.printStackTrace();
            }
         });
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
    
}
