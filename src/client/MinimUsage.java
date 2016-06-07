package client;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;


public class MinimUsage {
	Minim minim;
	AudioPlayer song;
	private MainApplet parent;
	
	public MinimUsage(MainApplet parent){//constructor
		this.parent = parent;
		minim = new Minim(this.parent);
		song = minim.loadFile("src/song/Kalimba.mp3");
	}
	
	public void playSong(){//play the song
		song.play();
	}
	

}
