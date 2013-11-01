package utils;

public class LinearType extends AnimationType {
	//f(x)=ax+b
	float a,b;
	public float getValue(long time){
		return (a*time+b);
	}
	public void createFunction(float minValue, float maxValue, long time){
		this.b=minValue;
		this.a=(maxValue-minValue)/time;
	}
}
