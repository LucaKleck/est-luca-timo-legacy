package framePackage;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class DrawnMapImage extends BufferedImage {
	private static int imageType = TYPE_INT_ARGB;
	private static Graphics2D g;

	public DrawnMapImage(int width, int height) {
		super(width, height, imageType);
		setG(this.createGraphics());
	}
	public static Graphics2D getG() {
		return g;
	}
	public static void setG(Graphics2D g) {
		DrawnMapImage.g = g;
	}
}
