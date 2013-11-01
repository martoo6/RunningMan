package runningMan;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

import utils.Random;
import mdes.slick.animation.Easing;
import mdes.slick.animation.Timeline;
import mdes.slick.animation.entity.LocationEntity;
import mdes.slick.animation.fx.LocationFx;

public class Bird implements LocationEntity{
	private Animation animation;
	private float x,y;
	private float size;
	private Timeline movement;
	
	public Bird(Animation animation){
		this.animation=animation;
		this.x=RunningMan.app.getWidth();
		this.y=Random.random(0, RunningMan.app.getHeight()/4);
//		this.size=Random.random(0.2, 0.4);
		this.size=1;
		
		setMovement();
	}
	
	private void setMovement(){
		movement = new Timeline();        
		movement.add(new LocationFx((int) Random.random(9000, 10000), this, this.x, this.y, 0-animation.getWidth(), this.y, Easing.LINEAR));
		movement.setActive(true);
	}
	
	public void update(int delta){
		this.movement.update(delta);
	}
	
	public void render(){
		animation.draw(x,y);
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

	public float getWidth() {
		return animation.getWidth();
	}
}
