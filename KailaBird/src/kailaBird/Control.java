package kailaBird;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Control extends KeyAdapter{
	
	Game game;
	
	public Control (Game game) {
		this.game = game;
	}
	
	public void keyPressed(KeyEvent e) {
		game.keyPressed(e);
	}
	
	public void keyRelease(KeyEvent e) {
		game.keyRelease(e);
	}
	
}
