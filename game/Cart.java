package game;

public class Cart {
    private String userID;
    private String gameID;
    private int quantity;
    private Game game;

    public Cart(String userID, String gameID, int quantity) {
        this.userID = userID;
        this.gameID = gameID;
        this.quantity = quantity;
    }

    public Cart getCart() {
        return this; // or whatever you want to return for the cart property
    }
    public Cart(String userID, String gameID, int quantity, Game game) {
        this.userID = userID;
        this.gameID = gameID;
        this.quantity = quantity;
        this.game = game;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
