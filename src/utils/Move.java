package utils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class Move extends TimerTask{
	public Collection<Animation> animations;
	private AnimationType DefaultAnimationType;
	Object object;
	Timer timer;
	private boolean initialized=false;
	
	public void init(Object object){
		if(initialized==false){
			animations= new LinkedList<Animation>();
			DefaultAnimationType=new LinearType();
			this.object=object;
			timer = new Timer("Animation");
			timer.schedule(this, 0, 1);
			this.initialized=true;
		}
	}
	
	//TODO: Para saber si esta o no en la coleccion y que no haga locuras (se puede eliminar la anterior, etc.
	public synchronized void isAttached(long time, String variable, float objetiveValue, AnimationType animationType){
		
	}
	
	public synchronized void attachVariable(long time, String variable, float objetiveValue, AnimationType animationType){
		try {
			//Field field=this.object.getClass().getField(variable);
			Field field=this.object.getClass().getDeclaredField(variable);
			field.setAccessible(true);
			Animation animation = new Animation(time,field,objetiveValue,animationType,this.object);
			synchronized(animations){
				animations.add(animation);
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}

	public void run(){
		//Como iterar y remover sacado de: http://docs.oracle.com/javase/tutorial/collections/interfaces/collection.html
		synchronized(animations){
			for (Iterator<Animation> it = animations.iterator(); it.hasNext(); ){
		        if (!it.next().animate()){		        	
		        	it.remove();
		        }
			}
		}
	}
	
	public synchronized void setDefaultAnimationType(AnimationType defaultAnimationType) {
		DefaultAnimationType = defaultAnimationType;
	}

}
