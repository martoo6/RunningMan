package utils;

public class SinFadeInOut2Type extends AnimationType {
	float minValue, maxValue; 
	double pendiente,pendiente2;
	int degree;
	
	public SinFadeInOut2Type(){
		degree=2;
	}
	public SinFadeInOut2Type(int degree){
		this.degree=degree;
	}
	
	public float getValue(long time){
		return (float) (pendiente2*Math.sin(pendiente*time)*Math.pow(time,degree)+minValue);
	}
	public void createFunction(float minValue, float maxValue, long duration){
		this.minValue=minValue;
		this.maxValue=maxValue-minValue;
		pendiente=(Math.PI/2)/duration;
		pendiente2=(maxValue-minValue)/Math.pow(duration,degree);
	}
}
