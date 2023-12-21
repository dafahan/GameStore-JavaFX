package game;


import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Home {

	VBox vboxs;
	GridPane gpane;
	Label hdLbl;
	
    private String userId;
    private Connect connect = Connect.getInstance();
    private TableView<Game> tableView = new TableView<>();
    HBox hboxJumlahLangkah;
    HBox hboxJarakTempuh ;
    HBox hboxDurasiLatihan;  
    HBox hbbtn;
    BorderPane bpmb, bphd, bptb;
    Menu md, ml;
	MenuBar mbar;
	MenuItem mh, mc, mlo;
    
    Button btnadd,btndelete;
    ObservableList<Game> gamelist = FXCollections.observableArrayList();
    
    private String tempId = null;
    
    private String getUsernameFromDatabase() {
        return "";
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void inittable() {
   
    	md = new Menu("Dashboard");
    	mh = new MenuItem("Home");
    	mc = new MenuItem("Cart");
    	
    	md.getItems().add(mh);
    	md.getItems().add(mc);
    	
    	ml = new Menu("Log out");
    	mlo = new MenuItem("Log out");
    	
    	ml.getItems().add(mlo);
    	
    	mbar = new MenuBar();
    	
    	mbar.getMenus().addAll(md, ml);
    	

    	String username = getUsernameFromDatabase();
    	hdLbl = new Label("Hello, " + username);
    	hdLbl.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        
    	gpane = new GridPane();
    	gpane.add(mbar, 0, 0);
    	
    	bpmb = new BorderPane();
    	bpmb.getChildren().add(mbar);

    	bpmb.setTop(gpane);
        bphd = new BorderPane();
        
    	bphd.setCenter(hdLbl);
    	
        bptb = new BorderPane();

    	bptb.setLeft(tableView);
        
//        btnadd.setOnAction(e-> {
//        	addData();
//        });
//        
//        btndelete.setOnAction(e -> {
//        	deleteData();
//        	
//        });
//        
//        m1.setOnAction(e-> {
//        	openHalamanTidur(userId);
//        });
//        
//        m2.setOnAction(e-> {
//        	openLoginPage();
//        });
//        
//        setupTableView(datafisik);
    }
    
//   private void openLoginPage() {
//		// TODO Auto-generated method stub
//    	  Stage currentStage = (Stage) vbfisik.getScene().getWindow(); 
//     	 currentStage.close(); 
//    	 Login loginpage = new Login();
//    	 loginpage.initialize();
//         Scene scene = new Scene(loginpage.getRoot(), 300, 250);
//         Stage stage = new Stage();
//         stage.setScene(scene);
//         stage.setTitle("SNeam");
//         stage.show();
//	}
//    
    public void getData() {
    	
     	String query = "SELECT GameName FROM game";
    	ResultSet resultSet = connect.execQuery(query);
    	
        try {
           
            while (resultSet.next()) {
                String game = resultSet.getString("GameName");
                
               // gamelist.add(new Game(game));
            }
        
        } catch (SQLException e) {
            e.printStackTrace();
    
        }
        
    	
    }
    
//    public void refreshTable() {
//    	getData();
//    	ObservableList<Fisik> fisObs = FXCollections.observableArrayList(datafisik);
//    	tableView.setItems(fisObs);
//    	
//    	refreshField();
//    }

//    public void refreshField() {
//    	tfdurasilatihan.setText("");
//    	tfjaraktempuh.setText("");
//    	tfJumlahLangkah.setText("");
//    	
//    	tempId = null;
//    }
    
    private void setupTableView(ObservableList<Game> gamelist) {
        TableColumn<Game, String> namagame = new TableColumn<>("");
        namagame.setCellValueFactory(new PropertyValueFactory<>(""));
        namagame.setPrefWidth(300);

        tableView.getColumns().addAll(namagame);
        tableView.setItems(gamelist);
        
        tableView.setOnMouseClicked(tableMouseEvent());
    }
    public EventHandler<MouseEvent> tableMouseEvent(){
    	return new EventHandler<MouseEvent>() {
    		@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				TableSelectionModel<Game> tableSelectionModel = tableView.getSelectionModel();
				
				tableSelectionModel.setSelectionMode(SelectionMode.SINGLE);
				
				Game nama = tableSelectionModel.getSelectedItem();
				}
		
    		
    	};
    }
//    public EventHandler<MouseEvent> tableMouseEvent(){
//    	return new EventHandler<MouseEvent>() {
//
//			@Override
//			public void handle(MouseEvent event) {
//				// TODO Auto-generated method stub
//				TableSelectionModel<Fisik> tableSelectionModel = tableView.getSelectionModel();
//				
//				tableSelectionModel.setSelectionMode(SelectionMode.SINGLE);
//				
//				Fisik halfisik = tableSelectionModel.getSelectedItem();
//				
//				if(halfisik != null) {
//				tfdurasilatihan.setText(String.valueOf(halfisik.getDurasiLatihan()));
//				tfjaraktempuh.setText(String.valueOf(halfisik.getJarakTempuh()));
//				tfJumlahLangkah.setText(String.valueOf(halfisik.getJumlahLangkah()));
//				
//				tempId = halfisik.getFisikId();
//				}
//			}
//    		
//    	};
//    	
//    }
//    
//    
//    private void openHalamanTidur(String userId) {
//  		// TODO Auto-generated method stub
//      	Stage currentStage = (Stage) vbfisik.getScene().getWindow();
//          currentStage.close();
//
//          HalamanTidur tidur = new HalamanTidur();
//          tidur.setUserId(userId); 
//          tidur.getData();
//          tidur.inittable();
//          VBox tidurlayout = tidur.getRoot();
//          Scene scene = new Scene(tidurlayout, 900, 600);
//
//          Stage stage = new Stage();
//          stage.setScene(scene);
//          stage.setTitle("Halaman Pemantau Kualitas Tidur");
//          stage.show();
           
//  	}
//    
    public VBox getRoot() {
    	
    	vboxs = new VBox(5);
    	vboxs.getChildren().addAll(bpmb, bphd, bptb);
    	
		return vboxs; 
    }

}
