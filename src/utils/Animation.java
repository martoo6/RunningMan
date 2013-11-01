package utils;

import java.lang.reflect.Field;

public class Animation{
	long totalTime;
	long time=0;
	Field variable; 
	float objetiveValue;
	AnimationType animationType;
	Object object;
	
	public Animation(long time, Field variable, float objetiveValue, AnimationType animationType,Object object){
		this.totalTime=time;
		this.variable= variable;
		this.objetiveValue=objetiveValue;
		this.animationType=animationType;
		this.object=object;
		try {
			animationType.createFunction(variable.getFloat(object), objetiveValue, time);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public boolean animate(){
		if(totalTime-time==0){
			return false;
		}
		this.time++;
		try {
			this.variable.setFloat(this.object, animationType.getValue(this.time));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return true;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public Field getVariable() {
		return variable;
	}

	public void setVariable(Field variable) {
		this.variable = variable;
	}

	public float getObjetiveValue() {
		return objetiveValue;
	}

	public void setObjetiveValue(float objetiveValue) {
		this.objetiveValue = objetiveValue;
	}

	public AnimationType getAnimationType() {
		return animationType;
	}

	public void setAnimationType(AnimationType animationType) {
		this.animationType = animationType;
	}
}
