package game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartModel {
    private Connect connect;

    public CartModel() {
        this.connect = Connect.getInstance();
    }

    public void addToCart(Cart cart) {
        try {
            String query = "INSERT INTO cart (UserID, GameID, Quantity) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connect.getConnection().prepareStatement(query)) {
                preparedStatement.setString(1, cart.getUserID());
                preparedStatement.setString(2, cart.getGameID());
                preparedStatement.setInt(3, cart.getQuantity());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception or log it as needed
        }
    }

    public ObservableList<Cart> getCart(String userID) {
        ObservableList<Cart> cartList = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM cart WHERE UserID = ?";
            try (PreparedStatement preparedStatement = connect.getConnection().prepareStatement(query)) {
                preparedStatement.setString(1, userID);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String gameID = resultSet.getString("GameID");
                        int quantity = resultSet.getInt("Quantity");

                        // Fetch the associated Game information
                        Game game = getGameById(gameID);

                        // Create a Cart object with Game information
                        Cart cart = new Cart(userID, gameID, quantity, game);
                        cartList.add(cart);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception or log it as needed
        }

        return cartList;
    }

    private Game getGameById(String gameID) {
        String query = "SELECT * FROM game WHERE GameID = ?";
        try (PreparedStatement preparedStatement = connect.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, gameID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String nama = resultSet.getString("GameName");
                    String description = resultSet.getString("GameDescription");
                    String price = resultSet.getString("Price");

                    return new Game(nama, gameID, description, price);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception or log it as needed
        }

        return null;
    }
    
     public Cart getGameCart(String userID, String gameID) {
        String query = "SELECT * FROM cart WHERE UserID = ? AND GameID = ?";
        try (PreparedStatement preparedStatement = connect.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, userID);
            preparedStatement.setString(2, gameID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int quantity = resultSet.getInt("Quantity");
                    // Fetch the associated Game information
                    Game game = getGameById(gameID);

                    // Create a Cart object with Game information
                    return new Cart(userID, gameID, quantity, game);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception or log it as needed
        }

        return null;
    }
     
      public void updateCart(Cart cart) {
        try {
            String query = "UPDATE cart SET Quantity = ? WHERE UserID = ? AND GameID = ?";
            try (PreparedStatement preparedStatement = connect.getConnection().prepareStatement(query)) {
                preparedStatement.setInt(1, cart.getQuantity());
                preparedStatement.setString(2, cart.getUserID());
                preparedStatement.setString(3, cart.getGameID());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception or log it as needed
        }
    }
      public void deleteCart(String userID, String gameID) {
    try {
        String query = "DELETE FROM cart WHERE UserID = ? AND GameID = ?";
        try (PreparedStatement preparedStatement = connect.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, userID);
            preparedStatement.setString(2, gameID);

            preparedStatement.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle the exception or log it as needed
    }
}

      
}
