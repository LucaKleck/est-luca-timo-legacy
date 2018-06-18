import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class DrawMap extends JPanel {
	private static final long serialVersionUID = 1L;
	private MapTile[][] map;
	private ObjectMap objectMap;
	public DrawMap(ObjectMap objectMap) {
		this.objectMap = objectMap;
		this.map = objectMap.getMap();
	}
	public void paint(Graphics g) {
		for(int y = 0; y < objectMap.getHeight(); y++) {
			for(int x = 0; x < objectMap.getWidth(); x++) {
				int width = this.getWidth()/objectMap.getWidth();
				int height = this.getHeight()/objectMap.getHeight();
				switch (map[x][y].getType()) {
				case 0:	g.setColor(Color.GREEN);
						g.fillRect(x*width, y*height, width, height);
						break;
				case 1: g.setColor(new Color(66,147,33));
						g.fillRect(x*width, y*height, width, height);
						break;
				case 2: g.setColor(new Color(200,200,200));
						g.fillRect(x*width, y*height, width, height);
						break;
				case 20:g.setColor(new Color(0,30,255));
						g.fillRect(x*width, y*height, width, height);
						break;
				default:g.setColor(Color.BLACK);
						g.fillRect(x*width, y*height, width, height);
						break;
				}
			}
		}
	}
}
