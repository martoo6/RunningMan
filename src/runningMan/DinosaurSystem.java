package runningMan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import mdes.slick.animation.Easing;
import mdes.slick.animation.Timeline;
import mdes.slick.animation.entity.LocationEntity;
import mdes.slick.animation.fx.LocationFx;

import org.lwjgl.Sys;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import utils.Random;

public class DinosaurSystem implements LocationEntity{
	long interval;
	long lastTime=0;
	Collection<Cloud> clouds= new ArrayList<Cloud>();
	Image [] imagenes = new Image[5];
	Timeline movement;
	float x,y;

	public DinosaurSystem(long interval) throws SlickException{
		this.interval=interval;
		imagenes[0]=new Image("resources/dinosaur.png");
		
		movement = new Timeline();        
		movement.add(new LocationFx(2500, this, -1000, 400, RunningMan.app.getWidth(), 400, Easing.LINEAR));
		movement.setActive(false);
	}
	
	public void generate(){
		if(getTime()-lastTime>interval){
			lastTime=getTime();
			
		}
	}
	
	public void update(int delta){
		movement.update(delta);
		if(getTime()-lastTime>interval){
			lastTime=getTime();
			interval=(long) Random.random(10000, 30000);
			movement.restart();
			movement.setActive(true);
		}
	}
	
	public void render(){
		imagenes[0].draw(x,y);
	}
	
	public long getTime() {
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public void setLocation(float x, float y) {
		this.x=x;
		this.y=y;
	}
}
