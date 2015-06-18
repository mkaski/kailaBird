package kailaBird;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Bird {
	
	private int WIDTH = 50;
	private int HEIGHT = 50;
	private int x;
	private int y;
	private double acceleration = 0.5;
	private double speed = 1;
	private BufferedImage bird;
	private boolean dead = false;
		
	public Bird(int x, int y, Game game) {
		
		this.y = y;
		this.x = x;
		bird = game.getBirdImg();
		this.dead = false;
		
	}
	
	/*
	 * Tick-metodi
	 * Jos lintu on sallittujen rajojen sis�ll�, se liikkuu(tippuu) x-akselia pitkin kiihtyv�ll� vauhdilla
	 * Muuten lintu kuolee
	 * 
	 */
	
	public void tick() {
		if ( ( y > -50 ) && ( y < Game.HEIGHT )) {
			// lintu tippuu kiihtyen jos sallittujen rajojen sis�ll�
			speed += acceleration;
			y += speed;
		}
		else {
			die();
		}
		
	}
	
	/*
	 * Kuolema, asettaa dead arvoksi true
	 * 
	 */
	
	public void die() {
		//System.out.println("KUOLI saatana");
		this.dead = true;		
	}
	
	/*
	 * Palauttaa boolean, onko lintu kuollut
	 * 
	 */
	
	public boolean isDead() {
		return this.dead;
	}
	
	/*
	 * Hyppy:
	 * Hidasta putoamisvauhti -9 y-akselilla, eli lintu pomppaa yl�sp�in hetkellisesti
	 * 
	 */
	
	public void jump() {
		speed = -9;
	}
	
	/*
	 * Piirr� lintu kohtaan x,y
	 * 
	 */
	
	public void render(Graphics g) {
		g.drawImage(bird,x,y,null);
	}
	
	/*
	 * Antaa linnun ��riviivat Rectangle-oliona
	 * Tietoa k�ytet��n t�rm�yksen tarkistamista varten
	 * 
	 */
	
	public Rectangle getBounds(){
		 return new Rectangle(x, y, WIDTH, HEIGHT);
		}
		
	/*
	 * Palauttaa linnun x-akselin sijainnin
	 * 
	 */
	
	public int getX() {
		return x;
	}
	
	/*
	 * Palauttaa linnun y-akselin sijainnin
	 * 
	 */
	
	public int getY() {
		return y;
	}
	
	/*
	 * Asettaa linnun y-akselin sijainnin
	 * 
	 */
	
	public void setY(int y) {
		this.y = y;
	}

}
