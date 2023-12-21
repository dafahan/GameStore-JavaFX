package game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameData {
    private static final ObservableList<Game> games = FXCollections.observableArrayList();

    static {
        // Load games from the database
        loadGamesFromDatabase();
    }

    private static void loadGamesFromDatabase() {
        // Clear the existing games to reload
        games.clear();

        String query = "SELECT * FROM game";
        ResultSet resultSet = Connect.getInstance().execQuery(query);

        try {
            while (resultSet.next()) {
                String gameID = resultSet.getString("GameID");
                String gameName = resultSet.getString("GameName");
                String gameDescription = resultSet.getString("GameDescription");
                String price = resultSet.getString("Price");

                Game game = new Game(gameName, gameID, gameDescription, price);
                games.add(game);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Game> getGames() {
        return games;
    }

    // Call this method when you want to refresh the games list from the database
    public static void refreshGames() {
        loadGamesFromDatabase();
    }
}
