package kailaBird;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music {
	
	private File file;
	private AudioInputStream stream;
	private AudioFormat format;
	private Clip clip;
	
	public Music() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		file = new File("res/kailaBird-Theme-loop.wav");
		stream = AudioSystem.getAudioInputStream(file);
		format = stream.getFormat();
		
		// specify what kind of line we want to create
		DataLine.Info info = new DataLine.Info(Clip.class, format);
		// create the line
		clip = (Clip)AudioSystem.getLine(info);
		// load the samples from the stream
		clip.open(stream);
		
	}
	
	public void play() {
		clip.start();
	}
	
	public void stop() {
		clip.stop();
	}
	
	public void loop() {
		clip.loop(5);
	}
	
	
}
