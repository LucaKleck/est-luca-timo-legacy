/**
 * @Classname DrawGui
 * 
 * @version Alpha
 * 
 * @author Luca Kleck, Timo Bauermeister
 * 
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
public class DrawGui extends JPanel {
	private JFrame f;
	public DrawGui( JFrame f ) {
		this.f = f;
	}
	private static final long serialVersionUID = 1L;
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		System.out.println("f Width: " + f.getWidth() + "  f Height: " + f.getHeight());
		Rectangle2D r = new Rectangle2D.Double(0, 0, f.getWidth(), f.getHeight());
		Paint(g2, r);
		
		
	}
	void Paint(Graphics2D g, Rectangle2D r) {
		float[] dist = {0.2f, 0.5f, 0.8f};
		Color[] colors = {Color.RED, Color.GREEN, Color.BLUE};
		LinearGradientPaint gp = new LinearGradientPaint((float) 0,(float)0,(float)r.getWidth(),(float)r.getHeight(),dist, colors);
	  g.setPaint(gp);
	  g.fill(r);
	}
	
}
