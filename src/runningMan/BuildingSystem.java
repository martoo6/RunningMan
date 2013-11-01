package runningMan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.lwjgl.Sys;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import utils.Random;

public class BuildingSystem {
	private long interval, newInterval;
	private long lastTime=0;
	private Collection<Building> buildings= new ArrayList<Building>();
	private Image [] imagenes = new Image[3];


	public BuildingSystem(long interval) throws SlickException{
		this.interval=interval;
		newInterval=(long) Random.random(interval-200,interval+200);
		imagenes[0]=new Image("resources/plataforma.png");
		imagenes[1]=new Image("resources/plataforma2.png");
		TreeSystem.init();
	}

	public void generate(){
		if((getTime()-lastTime)>newInterval){
			lastTime=getTime();
			newInterval=(long) Random.random(interval-200,interval+200);
			buildings.add(new Building(imagenes[(int)Random.random(2)]));
		}
	}

	public void update(int delta){
		for (Iterator<Building> it = buildings.iterator(); it.hasNext(); ){
			Building tmpBuilding = it.next();
			tmpBuilding.update(delta);
			if (tmpBuilding.getX()+tmpBuilding.getWidth()<=0){	
				it.remove();
			}
		}
		this.generate();
	}

	public void render(){
		for(Building building:buildings){
			building.render();
		}
	}
	
	public Collection<Building> getBuildings(){
		return this.buildings;
	}
	
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
}
