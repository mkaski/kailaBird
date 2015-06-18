package kailaBird;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;  
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;  


public class Highscore {
	
	private File file;
	private int hiscore;
	
	/*
	 * Highscore-konstruktori
	 * lukee tiedostosta hiscore.txt ensimmäisen rivin, jossa tallennettu huipputulos on kokonaislukuna
	 * 
	 */
	
	public Highscore() {
		
		//lue highscore tiedostosta
		try {
			this.file = new File("res/hiscore.txt");
			FileReader namereader = new FileReader(file);
			BufferedReader in = new BufferedReader(namereader);
			this.hiscore = Integer.parseInt(in.readLine());
			in.close();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Tallentaa huipputuloksen tiedostoon hiscore.txt ensimmäiselle riville, jos tulos parametrina annettu tulos
	 * on suurempi kuin konstruktorissa olioon tallennettu tulos.
	 * Jos tulos tallennetaan, muutetaan olion hiscore-muuttujaa vastaamaan uutta huipputulosta
	 */
	
	public void saveHiscore(int score) {
		
		if (score > this.hiscore) {
			try {
				  //tallenna tiedosto
				  FileWriter fstream = new FileWriter("res/hiscore.txt");
				  BufferedWriter out = new BufferedWriter(fstream);
				  out.write("" + score);
				  //close
				  out.close();
				  setHiscore(score);				  
				  }
			catch (Exception e){
				  System.err.println("Error: " + e.getMessage());
				 }
		}
		
		
	}
	
	/*
	 * Asettaa olion huipputulokseksi annetun parametrin
	 * 
	 */
	
	public void setHiscore(int score) {
		this.hiscore = score;
	}
	
	/*
	 * Antaa highscore-muuttujan
	 */
	
	public int getHiscore() {
				
		return this.hiscore;
		
	}

}
