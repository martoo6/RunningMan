package runningMan;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import utils.Random;

public class TreeSystem{
	private static Image [] trees = new Image[11];
	
	public static void init() throws SlickException{
		trees[0] = new Image("resources/trees/Arbol1.png");
		trees[1] = new Image("resources/trees/Arbol2.png");
		trees[2] = new Image("resources/trees/Arbol3.png");
		trees[3] = new Image("resources/trees/Arbol4.png");
		trees[4] = new Image("resources/trees/Arbol5.png");
		trees[5] = new Image("resources/trees/Arbol6.png");
		trees[6] = new Image("resources/trees/Arbol7.png");
		trees[7] = new Image("resources/trees/Arbol8.png");
		trees[8] = new Image("resources/trees/Arbol9.png");
		trees[9] = new Image("resources/trees/Arbol10.png");
		trees[10] = new Image("resources/trees/Arbol11.png");
	}
	
	public static Image getTree(){
		return trees[(int) Random.random(11)] ;
	}
}
