package application;

public class TreadTest implements Runnable{

	String name;
	Player player;
	
	public TreadTest(String name, Player player) {
		this.name = name;
		this.player = player;
	}
	
	@Override
	public void run() {
		try {
			System.out.println(name + " will sleep.");
			Thread.sleep(1000);
			player.setMediaWidth(player.getMedia().getWidth());
			player.setMediaHeight(player.getMedia().getHeight());
			System.out.println("tHeight: " + player.getMedia().getHeight());
			System.out.println("tWidth: " + player.getMedia().getWidth());
			System.out.println(name + " is done.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
