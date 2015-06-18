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
	 * Jos lintu on sallittujen rajojen sisällä, se liikkuu(tippuu) x-akselia pitkin kiihtyvällä vauhdilla
	 * Muuten lintu kuolee
	 * 
	 */
	
	public void tick() {
		if ( ( y > -50 ) && ( y < Game.HEIGHT )) {
			// lintu tippuu kiihtyen jos sallittujen rajojen sisällä
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
	 * Hidasta putoamisvauhti -9 y-akselilla, eli lintu pomppaa ylöspäin hetkellisesti
	 * 
	 */
	
	public void jump() {
		speed = -9;
	}
	
	/*
	 * Piirrä lintu kohtaan x,y
	 * 
	 */
	
	public void render(Graphics g) {
		g.drawImage(bird,x,y,null);
	}
	
	/*
	 * Antaa linnun ääriviivat Rectangle-oliona
	 * Tietoa käytetään törmäyksen tarkistamista varten
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
