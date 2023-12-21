package game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionModel {
    private Connect connect;

    public TransactionModel() {
        this.connect = Connect.getInstance();
    }

    public void addTransaction(String userID, ObservableList<Cart> cartList) {
        try {
            // Generate a unique TransactionID
            String transactionID = generateUniqueTransactionID();

            // Insert the transaction header
            String headerQuery = "INSERT INTO transactionheader (TransactionID, UserID) VALUES (?, ?)";
            try (PreparedStatement headerStatement = connect.getConnection().prepareStatement(headerQuery)) {
                headerStatement.setString(1, transactionID);
                headerStatement.setString(2, userID);
                headerStatement.executeUpdate();
            }

            // Insert the transaction details
            String detailQuery = "INSERT INTO transactiondetail (TransactionID, GameID, Quantity) VALUES (?, ?, ?)";
            try (PreparedStatement detailStatement = connect.getConnection().prepareStatement(detailQuery)) {
                for (Cart cart : cartList) {
                    detailStatement.setString(1, transactionID);
                    detailStatement.setString(2, cart.getGameID());
                    detailStatement.setInt(3, cart.getQuantity());
                    detailStatement.executeUpdate();
                    CartModel cartModel = new CartModel();
                    cartModel.deleteCart(cart.getUserID(), cart.getGameID());
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception or log it as needed
        }
    }

    
    public String generateUniqueTransactionID() {
    try {
        String query = "SELECT MAX(TransactionID) AS MaxID FROM transactionheader";
        try (PreparedStatement preparedStatement = connect.getConnection().prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String maxID = resultSet.getString("MaxID");
                    if (maxID != null) {
                        // Extract the numeric part and increment by 1
                        int numericPart = Integer.parseInt(maxID.substring(2));
                        return "TR" + (numericPart + 1);
                    }
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle the exception or log it as needed
    }

    // Default fallback (e.g., if there are no existing transactions)
    return "TR001";
}

}
