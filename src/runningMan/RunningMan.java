package runningMan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import mdes.slick.animation.entity.*;
import mdes.slick.animation.fx.*;
import mdes.slick.animation.*;

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.*;

public class RunningMan extends BasicGame{
	public static AppGameContainer app;
	private org.newdawn.slick.SpriteSheet mySheet;
	private Animation myAnimation;
	private int size=32;
	Character character;
	private boolean canJump=true;
	private Collection<Collisionable> buildings=new ArrayList<Collisionable>();
	private long lastTime=0;
	private long lastTime2=0;
	private Image cloud;
	private CloudSystem cloudSystem;
	private BuildingSystem buildingSystem;
	private BirdSystem birdSystem;
	private DinosaurSystem dinosaurSystem; 
	private Image mountain, sky;
	private MusicPlayer musicPlayer;
	
	private float x,y;
	
	public RunningMan()
	{
		super("RunningMan");
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		musicPlayer=new MusicPlayer();
		musicPlayer.init();
		mountain = new Image("resources/Fondo.PNG");
		sky = new Image("resources/sky.jpg");
		gc.setMinimumLogicUpdateInterval(1);
        gc.setMaximumLogicUpdateInterval(1);
        gc.setShowFPS(false);
		
		getAnimation();
		
		character=new Character(myAnimation,60,-100);
		cloudSystem = new CloudSystem(2000);
		buildingSystem = new BuildingSystem(1000);
		birdSystem= new BirdSystem(3000);
		dinosaurSystem = new DinosaurSystem(10000);
		//app.setTargetFrameRate(5);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		musicPlayer.poll(delta);
		buildingSystem.update(delta);		
		character.update(delta);
		character.checkCollisions(buildingSystem.getBuildings());
		cloudSystem.update(delta);
		birdSystem.update(delta);
		dinosaurSystem.update(delta);
		
		
		Input input = gc.getInput();
		
        if(input.isKeyDown(Input.KEY_ENTER)){
        	character.jump();
        }
        if(input.isKeyDown(Input.KEY_ESCAPE)){
        	gc.exit();
        }
	}

	public void render(GameContainer gc, Graphics g) throws SlickException{
		sky.draw(0,0,0.6f);
		mountain.draw(-20,140,0.9f);
		dinosaurSystem.render();
		buildingSystem.render();
		character.render();
		cloudSystem.render();
		birdSystem.render();
		g.drawString("Has sobrevivido por "+(character.getAliveTime()/1000)+" segundos", 20, 20);
	}
	
	
	private void getAnimation() throws SlickException {
		mySheet = new SpriteSheet("resources/spritesheet_caveman.png",size,size);

		myAnimation=new Animation(true);
		for(int i=0;i<4;i++){
			for(int e=3;e>=0;e--){
				myAnimation.addFrame(mySheet.getSprite(e, i), 50);
			}
		}
	}

//	private void getAnimation2() throws SlickException {
//		mySheet = new SpriteSheet("/home/martin/workspace diseño/Invaders/src/main/resources/PWLs Stickfigure Collection/Movement/shrooms_walk_strip6.png",56,56);
//
//		myAnimation=new Animation(true);
//		for(int e=0;e<6;e++){
//			myAnimation.addFrame(mySheet.getSprite(e, 0), 50);
//		}
//	}
	
	public long getTime() {
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	public static void main(String[] args) throws SlickException{
		app = new AppGameContainer( new RunningMan() );
		app.setDisplayMode(800, 600, false);
		app.start();
	}

}
