package game;


import javafx.collections.ObservableList;


public class Game {
	private String nama;
        private String id;
        private String description;
        private String Price;

    public Game(String nama, String description, String Price) {
        this.nama = nama;
        this.description = description;
        this.Price = Price;
    }

    public Game(String nama, String id, String description, String Price) {
        this.nama = nama;
        this.id = id;
        this.description = description;
        this.Price = Price;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        this.Price = Price;
    }
               
    public String toString(){
        return this.nama;
    }
    
    public static Game getGameById( ObservableList<Game> games, String id) {
        for (Game game : games) {
            if (game.getId() != null && game.getId().equals(id)) {
                return game;
            }
        }
        return null; 
    }
}
