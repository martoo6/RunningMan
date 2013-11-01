package runningMan;

import org.lwjgl.Sys;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import utils.Noise;
import utils.Random;
import mdes.slick.animation.Easing;
import mdes.slick.animation.Timeline;
import mdes.slick.animation.entity.LocationEntity;
import mdes.slick.animation.fx.LocationFx;

public class Building implements Collisionable, LocationEntity {
	float x,y,width,height;
	Timeline movement;
	Image plataforma;
	Image tree;
	int treePos;
	
	public Building(){
		this.x=Random.random(RunningMan.app.getWidth(), RunningMan.app.getWidth()+200);
		this.y=(float) (400+Noise.noise(getTime()*0.0002)*300);
		this.width=Random.random(200, 350);
		this.height=RunningMan.app.getHeight()-this.y;
		setMovement();
	}
	
	public Building(Image plataforma){
		this.plataforma=plataforma;
		this.x=RunningMan.app.getWidth();
		this.y=(float) (400+Noise.noise(getTime()*0.0002)*300);
		this.width=((int)Random.random(5, 10))*plataforma.getWidth();
		this.height=RunningMan.app.getHeight()-this.y;
		setMovement();
		if(Random.random(10)>=3){
			tree=TreeSystem.getTree();
			treePos=1 +(int) Random.random((this.width/plataforma.getWidth())-2);
		}
	}
	
	private void setMovement(){
		movement = new Timeline();        
		movement.add(new LocationFx(3000, this, this.x, this.y, -1-this.width, this.y, Easing.LINEAR));
		movement.setActive(true);
	}
	
	public void update(int delta){
		movement.update(delta);
	}
	
	public void render(){
		//RunningMan.app.getGraphics().setColor(Color.cyan);
		//Shape shape=new Rectangle(this.x, this.y, this.width, this.height);
		//RunningMan.app.getGraphics().draw(shape);
		for(int i=0;i<this.width/plataforma.getWidth();i++){
			plataforma.draw(x+i*plataforma.getWidth(), y);
		}
		if(tree!=null){			
			tree.draw(x+treePos*plataforma.getWidth(), y-tree.getHeight());
		}
	}
	
	public void setLocation(float x, float y) {
		this.x=x;
		this.y=y;
	}

	public boolean collition(Collisionable colObj) {
		if((colObj.getY()+colObj.getHeight()>=y)&&(colObj.getY()+colObj.getHeight()<=y+2)&&(colObj.getX()>=x)&&(colObj.getX()<=x+width)){
			return true;
		}
		return false;
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public long getTime() {
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
}
