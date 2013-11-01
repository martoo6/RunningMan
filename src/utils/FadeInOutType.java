package utils;

public class FadeInOutType extends AnimationType {
	float minValue, maxValue; 
	double pendiente,pendiente2;
	int degree;
	long duration;
	double a;
	
	public FadeInOutType(){
		degree=2;
	}
	public FadeInOutType(int degree){
		this.degree=degree;
	}
	
	public float getValue(long time){
		if(time<=(duration/2)){
			//return (float) (pendiente*Math.pow(time, degree)+minValue);
			//((6/2)/((4/2)^2))*(x^2)
			return (float) (a*Math.pow(time, degree)+minValue);
		}
//		if(time==duration){YA NO ES NECESARIO
//			return maxValue;
//		}
		//-((6/2)/((4/2)^2))*((x-4)^2)+6
		return (float) (-a*Math.pow(time-duration,degree)+maxValue);
		//return (float) (minValue+((maxValue-minValue)/2)+(pendiente2*(time-(duration/2)))-pendiente*Math.pow(time-(duration/2), degree));
	}
	public void createFunction(float minValue, float maxValue, long duration){
		this.minValue=minValue;
		this.maxValue=maxValue;
		this.duration=duration;
		pendiente=(maxValue-minValue)/(2*Math.pow(duration/2, degree));
		pendiente2=pendiente*duration;
		a=((maxValue-minValue)/2)/Math.pow((duration/2), degree);
	}
}
