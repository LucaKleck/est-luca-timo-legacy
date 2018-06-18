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
import javax.swing.JPanel;
public class DrawGui extends JPanel {
	private static final long serialVersionUID = 1L;
	public void paint(Graphics g) {
		//Graphics2D g2 = (Graphics2D) g;
		System.out.println("Width: " + this.getWidth() + "  Height: " + this.getHeight());
		
		//Rectangle2D r = new Rectangle2D.Double(0, 0, this.getWidth(), this.getHeight());
		//Paint(g2, r);
		/*for(int x=0; x<=this.getWidth();x++) {
			int y = Calc.Sinus.getYofX(this.getHeight(),this.getWidth(),x);
			g.drawLine(x, y, x, y);
		}*/
		
	}
	void Paint(Graphics2D g, Rectangle2D r) {
		float[] dist = {0.4f, 0.5f};
		Color[] colors = {Color.RED, Color.GREEN};
		LinearGradientPaint gp = new LinearGradientPaint((float) 0,(float)0,(float)r.getWidth(),(float)r.getHeight(),dist, colors);
	  g.setPaint(gp);
	  g.fill(r);
	}
	
}
