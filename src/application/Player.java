package application;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class Player extends BorderPane {
	
	Media media;
	MediaPlayer player;
	MediaView view;
	Pane mPane;
	MediaBar bar;
	int height;
	int width;
	Thread t1;
	
	public Player(String file) {
		media = new Media(file);
		player = new MediaPlayer(media);
		view = new MediaView(player);
		mPane = new Pane();
		mPane.getChildren().add(view);
		t1 = new Thread(new TreadTest("Test Thread", this));
		
		//t1.start();
				
		setCenter(mPane);
		
		bar = new MediaBar(player);
		setBottom(bar);
		setStyle("-fx-background-color: #bfc2c7");
		
		player.play();
		player.setOnReady(new Runnable() {

			@Override
			public void run() {
				width = media.getWidth();
				height = media.getHeight();
			}
			
		});
	}
	
	public MediaPlayer getPlayer() {
		return this.player;
	}

	public MediaView getMediaView() {
		return this.view;
	}

	public Media getMedia() {
		return this.media;
	}
	
	public int getMediaWidth() {
		return width;
	}
	
	public int getMediaHeight() {
		return height;
	}
	
	public void setMediaHeight(int height) {
		this.height = height;
	}
	
	public void setMediaWidth(int width) {
		this.width = width;
	}

//	@Override
//	public void run() {
//		try {
//			Thread.sleep(1000);
//			System.out.println("Thread done");
//			height = media.getHeight();
//			width = media.getWidth();
//			System.out.println("Height: " + getMediaHeight());
//			System.out.println("Width: " + getMediaWidth());
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	
}
