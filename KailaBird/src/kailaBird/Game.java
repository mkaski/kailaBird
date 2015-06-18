package kailaBird;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;


public class Game extends Canvas implements Runnable {
	
	static final int WIDTH = 600;
	static final int HEIGHT = 600;
	public final String TITLE = "Kaila Bird";
	
	private boolean running = false;
	private Thread thread;
	
	private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	private BufferedImage birdImg = null;
	
	private Bird b;
	private Wall wall = new Wall();
	private int score = 1;
	private Menu menu = new Menu();
	private Background back1 = new Background();
	private Highscore hiscore = new Highscore();
	
	public static STATE state = STATE.MENU;
	
	/*
	 * Pelin tila
	 */
	public static enum STATE {
		MENU,
		GAME
	};
	
	/*
	 * Alusta lintu ja nappaimist�kontrollit
	 * 
	 */
	
	public void init() {
		try {
			birdImg = ImageIO.read(new File("res/bird.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		addKeyListener(new Control(this));
		
		b = new Bird(150,100,this);
		
	}
	
	
	private synchronized void start() {
		if(running) {
			return;
		}
		else {
			running = true;
			thread = new Thread(this);
			thread.start();
		}
		
	}
	
	private synchronized void stop() {
		if (!running) {
			return;
		}
		else {
			running = false;
			try {
				thread.join();
			}
			catch (InterruptedException e){
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void run() {
		
		
		// alusta framelimit ja toista musiikki
		long lastTime = System.nanoTime();
		final double amountOfTicks = 50.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		init();
		
		try {
            Music music = null;
            try {
                music = new Music();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
            music.loop();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		
		
		// game looppi
		while (running) {
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			// kutsu ticki
			if (delta >= 1) {
				tick();
				delta--;
			}						
			render();
			
		}
		stop();
	}
	
	/*
	 * Kutsuu luokkien tick-metodeita
	 * Tarkastaa, onko lintu ohittanut sein��n ja kutsuu scorea, jos on
	 */
	
	private void tick() {
		if(state == STATE.GAME){
			b.tick();
			wall.tick(b);
			back1.tick();
			
			if (b.getX() == wall.getX()) {
				score();
				System.out.println(this.score);
			}
			
			if (b.isDead()) {
				saveScore();
			}
			
		}
	}
	
	/*
	 * Piirt�� kuvat ja tekstit
	 */
	
	private void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		// jos bufferstrategiaa ei ole, luodaan 3 kuvan bufferi
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(),this);
		
		if(state == STATE.GAME){
			back1.render(g);
			b.render(g);
			wall.render(g);
			g.setFont(new Font("arial", Font.BOLD, 50));
			g.setColor(new Color(47,113,241));
			g.drawString("" + score, WIDTH -50, 550);
		} else {
			menu.render(g,hiscore);
			
		}
		
		g.dispose();
		bs.show();
		
	}
	
	/*
	 * main-metodi, alustaa peliolion ja ikkunan ja k�ynnist�� pelin.
	 */
	
	public static void main(String args[]) {
		Game game = new Game();
		
		game.setSize(Game.WIDTH, Game.HEIGHT);
		
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.requestFocus();
		
		game.start();
		
	}
	
	/*
	 * Kasvattaa tulosta jos lintu ei ole kuollut
	 */
	
	public void score() {
		//kasvata tulosta jos lintu on elossa
		System.out.println("score");
		if (b.isDead() == false) {
			System.out.println("score2");
			this.score++;
		}
		
	}
	
	public void saveScore() {
		
		hiscore.saveHiscore(this.score);
	}
	
	
	/*
	 * Restart, alusta peli uudelleen
	 */
	
	public void reset() {
		this.b = new Bird(150,100,this);
		this.score = 1;
		this.wall = new Wall();
		this.hiscore = new Highscore();
	}
	
	
	public BufferedImage getBirdImg() {
		return birdImg;
	}
	
	/*
	 * N�pp�imet
	 * 
	 * ENTER: aloita peli
	 * SPACE: hypp��
	 * R: restart
	 * ESC: menu
	 * 
	 */
	
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_ENTER){
			this.reset();
			Game.state = STATE.GAME;
		}
				
		if (key == KeyEvent.VK_SPACE) {
			b.jump();
		}
		if(key == KeyEvent.VK_R){
			this.reset();
		}
		if(key == KeyEvent.VK_ESCAPE){
			Game.state = STATE.MENU;
		
		}
	}
	
	public void keyRelease(KeyEvent e) {
		
	}
}
