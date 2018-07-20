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
	private MapTile[][] map;
	private ObjectMap objectMap;
	private ArrayList<ActionListener> listener = null;
	private MapTile mapTile;
	private DrawMap drawMap;
	private Color color = Color.BLACK;
	private boolean selected = false;
	private boolean hover = false;
	private ActionListener drawMapTileActionListener;
	private DrawMapTile DrawMapTile;
	// Constructor
	public DrawMapTile(ObjectMap objectMap,int xOfTile, int yOfTile, MainJFrame mainJFrame, DrawMap drawMap) {
		this.objectMap = objectMap;
		this.DrawMapTile = this;
		this.map = objectMap.getMap();
		this.drawMap = drawMap;
		this.mapTile = map[xOfTile][yOfTile];
		this.color = mapTile.getMapTileType().getColor();
		listener = new ArrayList<ActionListener>();
        drawMapTileActionListener = new ActionListener() {
        	@Override // Event from DrawMap, contains command from the special happenings
        	public void actionPerformed(ActionEvent evt) {
        		@SuppressWarnings("unused")
				String result = mainJFrame.getCommandHandler().sendCommand(evt.getActionCommand(),DrawMapTile);
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
	public void drawMapTile(Graphics g) {
		mapTile.setHeight((double)drawMap.getHeight()/objectMap.getHeight());
		mapTile.setWidth((double)drawMap.getWidth()/objectMap.getWidth());
		try {
			g.setColor(color);
			g.fillRect((int)(mapTile.getWidth()*mapTile.getXPos()), (int)(mapTile.getHeight()*mapTile.getYPos()), (int)mapTile.getWidth()+1, (int)mapTile.getHeight()+1);
			g.setColor(new Color(0,0,0,40));
			g.drawRect((int)(mapTile.getWidth()*mapTile.getXPos()), (int)(mapTile.getHeight()*mapTile.getYPos()), (int)mapTile.getWidth()+1, (int)mapTile.getHeight()+1);
			if(selected) {
				g.setColor(new Color(255,0,0,100));
				g.fillRect((int)(mapTile.getWidth()*mapTile.getXPos()), (int)(mapTile.getHeight()*mapTile.getYPos()), (int)mapTile.getWidth()+1, (int)mapTile.getHeight()+1);
			}
			if(hover) {
				g.setColor(new Color(120,100,0,100));
				g.fillRect((int)(mapTile.getWidth()*mapTile.getXPos()), (int)(mapTile.getHeight()*mapTile.getYPos()), (int)mapTile.getWidth()+1, (int)mapTile.getHeight()+1);
			}
			if(mapTile.getBuilding() != null) {
				g.setColor(mapTile.getBuilding().getColor());
				g.fillRect((int)(mapTile.getWidth()*mapTile.getXPos()+mapTile.getWidth()/100*10), (int)(mapTile.getHeight()*mapTile.getYPos()+mapTile.getHeight()/100*10), (int)(mapTile.getWidth()-mapTile.getWidth()/100*40), (int)(mapTile.getHeight()-mapTile.getHeight()/100*40));
			}
		} catch (NullPointerException e) {
			
		}
	}
}