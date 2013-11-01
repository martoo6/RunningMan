package runningMan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.lwjgl.Sys;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import utils.Random;

public class MountainSystem {
	long interval;
	long lastTime=0;
	Collection<Cloud> clouds= new ArrayList<Cloud>();
	Image [] imagenes = new Image[5];
	

	public MountainSystem(long interval) throws SlickException{
		this.interval=interval;
		imagenes[0]=new Image("/home/martin/workspace diseño/Invaders/src/main/resources/cloud1.png");
		imagenes[1]=new Image("/home/martin/workspace diseño/Invaders/src/main/resources/cloud2.png");
		imagenes[2]=new Image("/home/martin/workspace diseño/Invaders/src/main/resources/cloud3.png");
		imagenes[3]=new Image("/home/martin/workspace diseño/Invaders/src/main/resources/cloud4.png");
		imagenes[4]=new Image("/home/martin/workspace diseño/Invaders/src/main/resources/cloud5.png");
	}
	
	public void generate(){
		if(getTime()-lastTime>interval){
			lastTime=getTime();
			clouds.add(new Cloud(imagenes[(int)Random.random(4)]));
		}
	}
	
	public void update(int delta){
		this.generate();
		for (Iterator<Cloud> it = clouds.iterator(); it.hasNext(); ){
			Cloud cloud = it.next();
			if (cloud.getX()+cloud.getWidth()<0){		        	
				it.remove();
			}else{
				cloud.update(delta);
			}
		}
	}
	
	public void render(){
		for(Cloud cloud:clouds){
			cloud.render();
		}
	}
	
	public long getTime() {
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
}
