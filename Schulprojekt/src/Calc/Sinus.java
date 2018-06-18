package Calc;

public class Sinus {
	public static int getYofX(int maxY, int maxX, int x) {
		double y;
		y = Math.sin((2*Math.PI)/maxX*x);
		y = -y*(maxY/2)+(maxY/2);
		return (int) y;
	}
}
