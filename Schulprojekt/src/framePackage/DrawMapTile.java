package framePackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import gameCore.ObjectMap;
import mapTiles.MapTile;

public class DrawMapTile extends JPanel implements MouseListener {
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
	public void drawMapTile(Graphics g) {
		mapTile.setHeight((double)drawMap.getHeight()/objectMap.getHeight());
		mapTile.setWidth((double)drawMap.getWidth()/objectMap.getWidth());
		try {
			this.setSize((int)drawMap.getWidth()/objectMap.getWidth(), (int)drawMap.getHeight()/objectMap.getHeight());
			//TODO add width/height multiplier for zoom
			double xPos = ( (mapTile.getWidth()	*mapTile.getXPos())+drawMap.getXDisplacement() )*drawMap.getDisplacementMultiplier();
			double yPos = ( (mapTile.getHeight()*mapTile.getYPos())+drawMap.getYDisplacement() )*drawMap.getDisplacementMultiplier();
			double height = (mapTile.getHeight()*drawMap.getDisplacementMultiplier() ) +1;
			double width = (mapTile.getWidth()*drawMap.getDisplacementMultiplier() ) +1;
			
			g.setColor(color);
			g.fillRect((int)xPos, (int)yPos, (int)width, (int)height);
			g.setColor(new Color(0,0,0,40));
			g.drawRect((int)xPos, (int)yPos, (int)width, (int)height);
			if(mapTile.getBuilding() != null) {//TODO cart to external method
				g.setColor(mapTile.getBuilding().getColor());
				g.fillRect((int)xPos, (int)yPos, (int)width, (int)height);
			}
			if(selected) {
				g.setColor(new Color(255,0,0,100));//TODO cart to external method
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
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}