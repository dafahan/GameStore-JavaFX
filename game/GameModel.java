package game;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GameModel {
    private Connection connection;

    public GameModel() {
     
        connect();
    }

    private void connect() {
        try {
          
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/projectbad";
            String username = "root";
            String password = "";
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
     public int getQuantity(String gameID, String userID) {
        int quantity = 0;

        try {
            // Create and execute the SQL statement
            String query = "SELECT Quantity FROM cart WHERE GameID = ? AND UserID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, gameID);
                preparedStatement.setString(2, userID);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    quantity = resultSet.getInt("Quantity");
                }
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quantity;
    }


    public ObservableList<Game> getAllGames() {
    ObservableList<Game> games = FXCollections.observableArrayList();

    try {
        // Create and execute the SQL statement
        String query = "SELECT * FROM game";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        // Process the result set
        while (resultSet.next()) {
            String id = resultSet.getString("GameID");
            String nama = resultSet.getString("GameName");
            String description = resultSet.getString("GameDescription");
            String price = resultSet.getString("Price");

            // Create a Game object and add it to the list
            Game game = new Game(id, nama, description, price);
            games.add(game);
        }

        // Close resources
        resultSet.close();
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return games;
}


    // You can implement other methods like getGameById, addGame, updateGame, deleteGame, etc.

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateGame(Game game) {
        try {
            // Create and execute the SQL statement
            String query = "UPDATE game SET GameName = ?, GameDescription = ?, Price = ? WHERE GameID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, game.getNama());
                preparedStatement.setString(2, game.getDescription());
                preparedStatement.setString(3, game.getPrice());
                preparedStatement.setString(4, game.getId());
                System.out.println(preparedStatement);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a game from the database
    public void deleteGame(String gameId) {
        try {
            // Create and execute the SQL statement
            String query = "DELETE FROM game WHERE GameID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, gameId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to create a new game in the database
    public String createGame(Game game) {
        String id ="";
        try {
            // Create and execute the SQL statement
            String query = "INSERT INTO game (GameID, GameName, GameDescription, Price) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                 id = generateUniqueTransactionID();
                preparedStatement.setString(1, generateUniqueTransactionID());
                preparedStatement.setString(2, game.getNama());
                preparedStatement.setString(3, game.getDescription());
                preparedStatement.setString(4, game.getPrice());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    public String generateUniqueTransactionID() {
    try {
        String query = "SELECT MAX(GameID) AS MaxID FROM game";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String maxID = resultSet.getString("MaxID");
                    if (maxID != null) {
                        // Extract the numeric part and increment by 1
                        int numericPart = Integer.parseInt(maxID.substring(2));
                        return "GA" + (numericPart + 1);
                    }
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Handle the exception or log it as needed
    }
    return "GA001";
    }



    }
