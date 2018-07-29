package framePackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import gameCore.ObjectMap;
import mapTiles.MapTile;

public class DrawMapTile extends JPanel {
	private static final long serialVersionUID = -8785925966340775096L;
	private DrawMapTile self;
	private MapTile[][] map;
	private ObjectMap objectMap;
	private ArrayList<ActionListener> listener = null;
	private MapTile mapTile;
	private DrawMap drawMap;
	private Color color = Color.BLACK;
	private boolean selected = false;
	private boolean hover = false;
	private ActionListener drawMapTileActionListener;
	// Constructor
	public DrawMapTile(ObjectMap objectMap,int xOfTile, int yOfTile, MainJFrame mainJFrame, DrawMap drawMap) {
		this.self = this;
		this.setBackground(new Color(0,0,0,0));
		this.drawMap = drawMap;
		this.objectMap = objectMap;
		this.map = objectMap.getMap();
		this.mapTile = map[xOfTile][yOfTile];
		this.color = mapTile.getMapTileType().getColor();
		listener = new ArrayList<ActionListener>();
        drawMapTileActionListener = new ActionListener() {
        	@Override // Event from DrawMap, contains command from the special happenings
        	public void actionPerformed(ActionEvent evt) {
        		@SuppressWarnings("unused")
				String result = mainJFrame.getCommandHandler().sendCommand(evt.getActionCommand(),self);
        		};
		};
		this.addActionListener(getDrawMapTileActionListener());
	}
	private ActionListener getDrawMapTileActionListener() {
		return drawMapTileActionListener;
	}
	public void removeSelectedFromAllTiles(MainJFrame mainJFrame) {
		for( int y = 0; y < map[0].length; y++) {
			for( int x = 0; x < map.length; x++) {
				DrawMapTile[][] drawMapTileArray = mainJFrame.getDrawMapTileArray();
				if(drawMapTileArray[x][y].selected == true) {
					drawMapTileArray[x][y].turnOffSelected();
					drawMap.repaintMapTile(x, y);
				}
			}
		}
	}
	public void removeSelectedFromAllTiles(MainJFrame mainJFrame, int xPos, int yPos) {
		for( int y = 0; y < map[0].length; y++) {
			for( int x = 0; x < map.length; x++) {
				DrawMapTile[][] drawMapTileArray = mainJFrame.getDrawMapTileArray();
				if(yPos == y && xPos == x) {
					drawMap.repaintMapTile(xPos, yPos);
				} else {
					if(drawMapTileArray[x][y].selected == true) {
					drawMapTileArray[x][y].turnOffSelected();
					drawMap.repaintMapTile(x, y);
					}
				}
			}
		}
	}
	public void toggleHover() {
		hover = !hover;
	}
	public void toggleSelected() {
		selected = !selected;
	}
	public void turnOffSelected() {
		selected = false;
	}
	public MapTile getMapTile() {
		return mapTile;
	}
	public boolean getSelected() {
		return selected;
	}
	protected void fireUpdate(ActionEvent evt) {   
        for (ActionListener al : listener) {
            al.actionPerformed(evt);
        }
    }
    public void addActionListener(ActionListener al) {
        listener.add(al);
    }
    public void removeActionListener(ActionListener al) {
        listener.remove(al);
    }
	public ArrayList<ActionListener> getListener() {
		return listener;
	}
	public void drawMapImage() {
		// draws into the buffered image DrawMapImage, this image will then be used by DrawMap to change positions and so on, the timer of the MainJFrame will regularly update the image, changes to the mapTiles will now have to redirected over this function
		Graphics g = DrawnMapImage.getG();
		mapTile.setHeight((double)drawMap.getHeight()/objectMap.getHeight());
		mapTile.setWidth((double)drawMap.getWidth()/objectMap.getWidth());
		try {
			

			double xPos = mapTile.getWidth()*mapTile.getXPos();
			double yPos = mapTile.getHeight()*mapTile.getYPos();
			double height = mapTile.getHeight() +1;
			double width = mapTile.getWidth()  +1;
			this.setSize((int)width, (int)height);
			
			// the tile itself
			g.setColor(color);
			g.fillRect((int)xPos, (int)yPos, (int)width, (int)height);
			
			// the border of the tile
			g.setColor(new Color(0,0,0,40));
			g.drawRect((int)xPos, (int)yPos, (int)width, (int)height);
			
			if(mapTile.getBuilding() != null) {
				g.setColor(mapTile.getBuilding().getColor());
				g.fillRect((int)xPos, (int)yPos, (int)width, (int)height);
			}
			if(selected) {
				g.setColor(new Color(255,0,0,100));
				g.fillRect((int)xPos, (int)yPos, (int)width, (int)height);
			}
			if(hover) {
				g.setColor(new Color(120,100,0,100));
				g.fillRect( (int)xPos, (int)yPos, (int)width, (int)height );
			}
			super.paint(g);
		} catch (NullPointerException e) {
			
		}
		
	}
}