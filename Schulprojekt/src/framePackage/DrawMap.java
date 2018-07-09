package framePackage;
import javax.swing.JPanel;

public class DrawMap extends JPanel {
	private static final long serialVersionUID = 1L;
	/*private MapTile[][] map;
	private ObjectMap objectMap;
	public DrawMap(ObjectMap objectMap) {
		this.objectMap = objectMap;
		this.map = objectMap.getMap();
	}

	public void paint(Graphics g) {
		double width = (double)this.getWidth()/objectMap.getWidth();
		double height = (double)this.getHeight()/objectMap.getHeight();
		
		Color color = Color.BLACK;
		for(int y = 0; y < objectMap.getHeight(); y++) {
			for(int x = 0; x < objectMap.getWidth(); x++) {
				map[x][y].setHeight(height);
				map[x][y].setWidth(width);				
				switch(map[x][y].getType()) {
					case 0:	color = new Color(56, 216, 59);
							break;
					case 1: color = new Color(66,147,33);
							break;
					case 2: color = new Color(10,130,10);
							break;
					case 3: color = new Color(0,100,0);
							break;
					case 20:color = new Color(0,30,255);
							break;
					default:color = Color.BLACK;
							break;
				}
				//map[x][y].drawMapTile(g, color);
				//drawMapTile(g,map[x][y],color);
			}
		}
	}
	public void drawMapTile(Graphics g, MapTile map, Color color) {
		g.setColor(color);
		g.fillRect((int)((double)map.getXPos()*map.getWidth()), (int)(map.getYPos()*map.getHeight()), (int)map.getWidth()+1, (int)map.getHeight()+1);
		g.setColor(new Color(0,0,0,40));
		g.drawRect((int)((double)map.getXPos()*map.getWidth()), (int)(map.getYPos()*map.getHeight()), (int)map.getWidth()+1, (int)map.getHeight()+1);
	}*/
}
