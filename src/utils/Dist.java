package utils;

public class Dist {
	public static double dist(double x1, double y1, double x2, double y2){
		return Math.sqrt(Math.pow((x1-x2), 2)+Math.pow((y1-y2), 2));
	}
	public static double dist(double x1, double y1, double z1, double x2, double y2, double z2){
		return Math.sqrt(Math.pow((x1-x2), 2)+Math.pow((y1-y2), 2)+Math.pow((z1-z2), 2));
	}
}
