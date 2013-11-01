package utils;

public class SinFadeInOutType extends AnimationType {
	float minValue, maxValue; 
	double pendiente;
	int degree;
	
	public SinFadeInOutType(){
		degree=2;
	}
	public SinFadeInOutType(int degree){
		this.degree=degree;
	}
	
	public float getValue(long time){
		return (float) (maxValue*Math.pow(Math.sin(pendiente*time),degree)+minValue);
	}
	public void createFunction(float minValue, float maxValue, long duration){
		this.minValue=minValue;
		this.maxValue=maxValue-minValue;
		pendiente=(Math.PI/2)/duration;
	}
}
