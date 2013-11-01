package utils;

public final class Random {
	private static java.util.Random randGen=new java.util.Random();
	public static float random(double max){		
		//return (float) (Math.random() * (max + 1));
		return (float) (randGen.nextFloat() * max);
	}
	public static float random(double min, double max){
		//return (float) (min + Math.random() * (max - min + 1));
		return (float) (min + randGen.nextFloat() * (max - min));
	}
}
