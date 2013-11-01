package runningMan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.newdawn.slick.Music;
import org.newdawn.slick.MusicListener;
import org.newdawn.slick.SlickException;

import utils.Random;

public class MusicPlayer extends RunningMan implements MusicListener{
	private String [] songs={
		"Wonder Boy III Music (SMS) - Endless War.ogg",
		"Wonder Boy III Music (SMS) - It's a Treasure Box.ogg",
		"Wonder Boy III Music (SMS) - Mind of Hero.ogg",
		"Wonder Boy III Music (SMS) - Monster-Town.ogg",
		"Wonder Boy III Music (SMS) - Mouse-Man Falling.ogg",
		"Wonder Boy III Music (SMS) - Side-Crawler's Dance.ogg",
		"Wonder Boy III Music (SMS) - The Danger Zone.ogg",
		"Wonder Boy III Music (SMS) - The Dragon's Trap.ogg",
		"Wonder Boy III Music (SMS) - The Last Dungeon.ogg",
		"Wonder Boy III Music (SMS) - The Monster's Lair.ogg"
};
	private List<Music> music=new ArrayList<Music>();
	private int currentSong=(int) Random.random(9);
	
	public void init() throws SlickException{
		for(int i=0;i<songs.length;i++){
			Music musicTmp=new Music("resources/music/"+songs[i]);
			musicTmp.addListener(this);
			music.add(musicTmp);
			
		}
		music.get(currentSong).play(1,0.8f);
	}
	
	public void poll(int delta){
		music.get(currentSong).poll(delta);
	}
	
	@Override
	public void musicEnded(Music music) {
		currentSong++;
		if(currentSong==9){
			currentSong=0;
		}
		this.music.get(currentSong).play(1,0.8f);
	}

	@Override
	public void musicSwapped(Music arg0, Music arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
