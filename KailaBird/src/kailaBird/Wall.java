package kailaBird;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

public class Wall {
	
	final private int WIDTH = 48;
	final private int GAP = 150;
	
	private Random rnd = new Random();
	private int x = 500;
	private int y = rnd.nextInt((600) - GAP) + GAP;
	private int speed = 6;
	private int HEIGHT = Game.HEIGHT - y;
	private BufferedImage wallImg = null;
	
	/*
	 * Wall-konstruktori:
	 * lataa sein‰n kuva
	 */
	
	public Wall() {
		
		// lataa sein‰kuva
		try {
			wallImg = ImageIO.read(new File("res/wall.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Piirt‰‰ sein‰n alaosan ja yl‰osan suhteessa alasein‰n korkeuteen vakiona pysyv‰n GAP-muuttujan eli aukon koon huomioon ottaen
	 * 
	 */
	
	public void render(Graphics g){
		
		// alaosa
		g.drawImage(wallImg, x, y, null);
		// ylaosa
		g.drawImage(wallImg, x, ( -Game.HEIGHT ) + ( y - GAP), null);
	}
	
	/*
	 * Tick-metodi:
	 * sein‰ liikkuu x-akselilla vasemmalle speed-muuttujan arvon mukaan
	 * Tarkastetaan, onko parametrina annettu Bird-olion ja Wall-olioiden ‰‰riviivat p‰‰llekk‰in
	 * Jos ‰‰riviivat ovat p‰‰llekk‰in, lintu kuolee ja sein‰ kuolee (kutsutaan olioiden die-metodia)
	 * Kun sein‰ on liikkunut vasemmalle kokonaan n‰kyv‰n alueen ulkopuolelle, siirret‰‰n se oikealle puolelle ja alustetaan uudet satunnaiset arvot
	 */
	
	public void tick(Bird b){
		
		x -= speed;
		
		// seinien ‰‰riviivat
		Rectangle wallBounds = new Rectangle(x, y, WIDTH, HEIGHT);
		Rectangle wallBoundsTop = new Rectangle(x, 0, WIDTH, Game.HEIGHT - ( HEIGHT + GAP));
		
		// tˆrm‰ys
		if (wallBounds.intersects(b.getBounds()) || wallBoundsTop.intersects(b.getBounds())){
			b.die();
			this.die();
		}
		
		if (x <= 0 - WIDTH){
			x = Game.WIDTH;
			y = rnd.nextInt((600) - GAP) + GAP;
			HEIGHT = Game.HEIGHT - y;
		}
		
	}
	
	/*
	 * Asettaa sein‰n liikkumisnopeus 0, sein‰ pys‰htyy
	 * 
	 */
	
	public void die() {
		this.speed = 0;
	}
	
	/*
	 * Antaa sein‰n sijainnin x-akselilla
	 * 
	 */
	
	public int getX() {
		return this.x;
	}
	
		
}
