package kailaBird;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Background {
	
	private int x1 = 0;
	private int x2 = 600;
	private int speed = 1;
	private BufferedImage back1;
	private BufferedImage back2;
	
	public Background(){
		try {
			back1 = ImageIO.read(new File("res/testitausta.png"));
			back2 = ImageIO.read(new File("res/tausta2.png"));
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render(Graphics g){
		g.drawImage(back1, this.x1, 0, null);
		g.drawImage(back2, this.x2, 0, null);
	}
	
	public void tick(){
		this.x1 = x1 - speed;
		this.x2 = x2 - speed;
		if(x1 < -600){
			this.x1 = 590;
		}
		if(x2 < -600){
			this.x2 = 590;
		}
	}
	
}
