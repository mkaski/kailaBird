package kailaBird;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

public class Wall2 {
	
	private Random rnd = new Random();
	private int x = 500;
	// alasein�n yl�reuna
	private int y;
	private int speed = 5;
	// alasein�n korkeus
	private int height = Game.HEIGHT - y;
	
	private final int width = 60;
	private final int gap = 100;
	
	private BufferedImage wallImg = null;
	
	public Wall2() {
		
		// lataa sein�kuva
		try {
			wallImg = ImageIO.read(new File("res/wall.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render() {
		
	}
	
	public void tick(Bird b) {
		
	}
	
	
	

}
