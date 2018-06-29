import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import mapTiles.MapTile;

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
				double width = (double)this.getWidth()/objectMap.getWidth();
				double height = (double)this.getHeight()/objectMap.getHeight();
				switch (map[x][y].getType()) {
				case 0:	g.setColor(new Color(56, 216, 59));
						g.fillRect((int)(x*width), (int)(y*height), (int)width+1, (int)height+1);
						break;
				case 1: g.setColor(new Color(66,147,33));
						g.fillRect((int)(x*width), (int)(y*height), (int)width+1, (int)height+1);
						break;
				case 2: g.setColor(new Color(10,130,10));
						g.fillRect((int)(x*width), (int)(y*height), (int)width+1, (int)height+1);
						break;
				case 3: g.setColor(new Color(0,100,0));
						g.fillRect((int)(x*width), (int)(y*height), (int)width+1, (int)height+1);
						break;
				case 20:g.setColor(new Color(0,30,255));
						g.fillRect((int)(x*width), (int)(y*height), (int)width+1, (int)height+1);
						break;
				default:g.setColor(Color.BLACK);
						g.fillRect((int)(x*width), (int)(y*height), (int)width+1, (int)height+1);
						break;
				}
			}
		}
	}
}
