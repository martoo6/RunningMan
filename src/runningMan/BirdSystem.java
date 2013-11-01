package runningMan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.lwjgl.Sys;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import utils.Random;

public class BirdSystem{

	long interval;
	long lastTime=0;
	Collection<Bird> birds= new ArrayList<Bird>();
	Animation [] animations= new Animation[4];
	SpriteSheet [] sheets = new SpriteSheet[3];


	public BirdSystem(long interval) throws SlickException{
		this.interval=interval;
		sheets[0] = new SpriteSheet("resources/animals.png",32,32);
		sheets[1] = new SpriteSheet("resources/birds.png",32,32);
		sheets[2] = new SpriteSheet("resources/terodactile.png",32,64);
		
		for(int e=0;e<animations.length;e++){
			animations[e]=new Animation(true);			
		}
		
		for(int e=6;e<9;e++){
			animations[0].addFrame(sheets[0].getSprite(e, 1), 100);
		}
		for(int i=1;i<3;i++){
			for(int e=0;e<4;e++){
				animations[i].addFrame(sheets[1].getSprite(e, i-1), 100);
			}
		}
		for(int e=0;e<4;e++){
			animations[3].addFrame(sheets[2].getSprite(e, 0), 100);
		}
	}

	public void generate(){
		if(getTime()-lastTime>interval){
			lastTime=getTime();
			birds.add(new Bird(animations[(int)Random.random(4)]));
		}
	}

	public void update(int delta){
		this.generate();
		for (Iterator<Bird> it = birds.iterator(); it.hasNext(); ){
			Bird bird = it.next();
			if (bird.getX()+bird.getWidth()<0){		        	
				it.remove();
			}else{
				bird.update(delta);
			}
		}
	}

	public void render(){
		for(Bird bird:birds){
			bird.render();
		}
	}

	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

}
