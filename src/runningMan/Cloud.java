package runningMan;

import mdes.slick.animation.Easing;
import mdes.slick.animation.Timeline;
import mdes.slick.animation.entity.LocationEntity;
import mdes.slick.animation.fx.LocationFx;

import org.newdawn.slick.Image;

import utils.Random;

public class Cloud implements LocationEntity{
	private Image imagen;
	private float x,y;
	private float size;
	private Timeline movement;
	
	public Cloud(Image imagen){
		this.imagen=imagen;
		this.x=RunningMan.app.getWidth();
		this.y=Random.random(0, RunningMan.app.getHeight()/4);
		this.size=Random.random(0.2, 0.4);
		setMovement();
	}
	
	private void setMovement(){
		movement = new Timeline();        
		movement.add(new LocationFx((int) Random.random(9000, 10000), this, this.x, this.y, 0-imagen.getWidth(), this.y, Easing.LINEAR));
		movement.setActive(true);
	}
	
	public void update(int delta){
		this.movement.update(delta);
	}
	
	public void render(){
		imagen.draw(x,y,size);
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setLocation(float x, float y) {
		this.x=x;
		this.y=y;
	}
	
	public float getWidth(){
		return this.imagen.getWidth();
	}
}
