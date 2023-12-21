package game;


import java.util.Iterator;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddcartController{
     @FXML
    private AnchorPane dkk;
    private boolean isInCart;
    @FXML
    private Button addCart;

    @FXML
    private TextField amount;

    @FXML
    private Label description;

    @FXML
    private Label name;

    @FXML
    private Label price;

    public ObservableList<Cart> setDetail(Game game, ObservableList<Cart> orderList, User user) {
        System.out.println(game);
     isInCart = false;
    description.setText(game.getDescription());
    name.setText(game.getNama());
    price.setText("Rp. " + game.getPrice());
    final CartModel cartModel = new CartModel();
    Cart cart = cartModel.getGameCart(user.getUserId(), game.getId());

    if (cart != null) {
        // Cart already exists, update the UI and set isInCart to true
        amount.setText(String.valueOf(cart.getQuantity()));
        isInCart = true;
    }else{
        isInCart = false;
    }

    addCart.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            int newQuantity = Integer.parseInt(amount.getText());
            
            if (isInCart) {
                
                Iterator<Cart> iterator = orderList.iterator();
                    while (iterator.hasNext()) {
                        Cart existingCart = iterator.next();

                        if (existingCart.getUserID().equals(user.getUserId()) && existingCart.getGameID().equals(game.getId())) {
                            if (newQuantity == 0) {
                                // Remove from orderList and delete from the database
                                iterator.remove();
                                cartModel.deleteCart(existingCart.getUserID(), existingCart.getGameID());
                            } else {
                                // Update quantity in existingCart and update in the database
                                existingCart.setQuantity(newQuantity);
                                cartModel.updateCart(existingCart);
                            }
                            break; // No need to continue searching once updated or removed
                        }
                    }
                 
            } else {
              
                if(newQuantity==0){
                    closePage();
                }else{
                // Cart does not exist, add a new cart to the orderList
                Cart newCart = new Cart(user.getUserId(), game.getId(), newQuantity,game);
                cartModel.addToCart(newCart);
                orderList.add(newCart);
                }
            }
            closePage();
        }
        
    });
        return orderList;
    
}
    public void closePage(){
     ((Stage) dkk.getScene().getWindow()).close();   
    }

}
