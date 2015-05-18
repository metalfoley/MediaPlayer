package application;
	
import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class Main extends Application{
	
	Player player;
	FileChooser fileChooser;
	int playerWidth;
	int playerHeight;
	//Thread t1 = new Thread(new TreadTest("Test Thread"));
	
	public void start(Stage primaryStage) {
		
		MenuItem open = new MenuItem("Open");
		MenuItem fullScreen = new MenuItem("Full Screen");
		Menu file = new Menu("File");
		Menu view = new Menu("View");
		MenuBar menu = new MenuBar();
		
		file.getItems().add(open);
		menu.getMenus().add(file);
		view.getItems().add(fullScreen);
		menu.getMenus().add(view);
		
		fileChooser = new FileChooser();
		
		open.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				File file = fileChooser.showOpenDialog(primaryStage);
				if(file != null) {
					try {
						player.player.pause();
						player = new Player(file.toURI().toURL().toExternalForm());
						setNewSceneSize(player, menu, primaryStage);
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		player = new Player("file:///Users/stevenbyington/Desktop/test.mp4");
		setNewSceneSize(player, menu, primaryStage);
		
	}
	
	private void resizePlayer(Scene scene, MenuBar menu) {
		player.getMediaView().fitHeightProperty().bind(scene.heightProperty());
		player.getMediaView().fitWidthProperty().bind(scene.widthProperty());
		player.setTop(menu);
		player.getMediaView().setPreserveRatio(true);
	}
	
	private void setNewSceneSize(Player player, MenuBar menu, Stage primaryStage) {
		player.setTop(menu);
		player.getPlayer().setOnReady(new Runnable() {
			
			@Override
			public void run() {
				Scene scene = new Scene(player, player.getPlayer().getMedia().getWidth(), player.getPlayer().getMedia().getHeight() + 65, Color.BLACK);
				resizePlayer(scene, menu);
				primaryStage.setScene(scene);
				if(primaryStage.isShowing()) {
					return;
				} else {
					primaryStage.setTitle("Steven's Movie Player");
					primaryStage.show();
				}
			}
			
		});

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
