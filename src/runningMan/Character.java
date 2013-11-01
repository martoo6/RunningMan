package runningMan;

import java.util.Collection;

import org.lwjgl.Sys;
import org.newdawn.slick.Animation;

import mdes.slick.animation.Easing;
import mdes.slick.animation.Timeline;
import mdes.slick.animation.entity.AlphaEntity;
import mdes.slick.animation.entity.FxEntity;
import mdes.slick.animation.entity.LocationEntity;
import mdes.slick.animation.fx.AlphaFx;
import mdes.slick.animation.fx.LocationFx;

public class Character implements LocationEntity, AlphaEntity, Collisionable{
	private float x,y;
	Animation animation;
	private Timeline jump=new Timeline(), bounce=new Timeline(), fall=new Timeline(), blink=new Timeline();
	private boolean canJump, canFall, canBounce;
	private float jumpDistance=150; 
	private float jumpTime=2;
	boolean colisiono;
	private long lastTime=0;
	private long interval=160;
	private long aliveTime=0;
	private float alpha;
	
	public Character(Animation animation, float x, float y){
		this.animation=animation;
		this.x=x;
		this.y=y;
		this.canJump=false;
		this.canFall=true;
		this.fall();
		
		for(int i=0;i<4;i++){
			blink.add(new AlphaFx(100, this, 1, 0.1f, Easing.LINEAR));
			blink.add(new AlphaFx(150, this, 0.1f, 1, Easing.LINEAR));	
		}
		
		
		blink.setActive(true);
		blink.setLooping(false);
	}
	
	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}

	public void setLocation(float x, float y) {
		this.x=x;
		this.y=y;
	};
	
	public void render(){
		animation.getCurrentFrame().setAlpha(alpha);
		animation.draw(this.x, this.y);
	}
	
	public void update(int delta){
		jump.update(delta);
		bounce.update(delta);
		fall.update(delta);
		blink.update(delta);
		if(this.y>=RunningMan.app.getHeight()){
			y=200;
			canJump=false;
			canBounce=true;
			canFall=true;
			this.fall();
			aliveTime=0;
			blink.restart();
			blink.setActive(true);
		}
		aliveTime+=delta;
	}
	
	public boolean canJump(){
		return canJump;
	}
	
	public void jump(){
		if(canJump){
			jump = new Timeline();        
			
			jump.add(new LocationFx((int) (jumpDistance*jumpTime), this, this.x, this.y, this.x, this.y-jumpDistance, Easing.QUAD_OUT));
			jump.add(new LocationFx((int) ((RunningMan.app.getHeight()-(this.y-jumpDistance))*jumpTime), this, this.x, this.y-jumpDistance, this.x, 600, Easing.QUAD_IN));
			
			
			bounce.setActive(false);
			jump.setActive(true);

			canJump=false;
			canFall=false;
			canBounce=true;
		}
	}
	
	private void bounce(){
		if(canBounce){
			bounce = new Timeline();        
			
	        bounce.add(new LocationFx(50, this, this.x, this.y, this.x, this.y-10, Easing.QUARTIC_IN_OUT));
	        bounce.add(new LocationFx(50, this, this.x, this.y-10, this.x, this.y, Easing.QUAD_IN));
	        bounce.add(new LocationFx(25, this, this.x, this.y, this.x, this.y-5, Easing.QUARTIC_IN_OUT));
	        bounce.add(new LocationFx(25, this, this.x, this.y-5, this.x, this.y, Easing.QUAD_IN));
	        
	        jump.setActive(false);
	        fall.setActive(false);
	        bounce.setActive(true);
	        canJump=true;
	        canFall=true;
	        canBounce=false;
	        lastTime=getTime();
		}
	}

	private void fall(){
		if(canFall){
			fall = new Timeline();        
			fall.add(new LocationFx((int) ((RunningMan.app.getHeight()-this.y)*jumpTime), this, this.x, this.y, this.x, RunningMan.app.getHeight(), Easing.QUAD_IN));
			fall.setActive(true);
			canFall=false;
			canJump=false;
			canBounce=true;
		}
	}
	
	public void checkCollisions(Collection<Building> collection){
		if(getTime()-lastTime>interval){
			colisiono=false;
			for(Collisionable colObj:collection){
				if(colObj.collition(this)){				
					this.bounce();
					colisiono=true;
				}
			}
			if((!colisiono)&&(canJump)){
				this.fall();
			}
		}else{
			colisiono=true;
		}
	}
	
	public long getAliveTime(){
		return this.aliveTime;
	}
	
	public boolean collition(Collisionable colObj) {
		return false;
	}

	public float getWidth() {
		return 10;
	}

	public float getHeight() {
		return 32;
	}
	
	public long getTime() {
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	@Override
	public float getAlpha() {
		return alpha;
	}

	@Override
	public void setAlpha(float alpha) {
		this.alpha=alpha;
	}
}
