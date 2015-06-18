package kailaBird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.io.File;

public class Menu extends JPanel {
	
		private static final long serialVersionUID = 1L;
		private BufferedImage background;
		
		public Menu(){

			try {
				background = ImageIO.read(new File("res/menu.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		public void render(Graphics g,Highscore h){
			g.drawImage(background, 0, 0, null);
			g.setFont(new Font("arial", Font.PLAIN, 30));
			g.setColor(new Color(47,113,241));
			g.drawString(""+ h.getHiscore(), 265, 540);
		}
		
		public void tick(){}
		
		
}