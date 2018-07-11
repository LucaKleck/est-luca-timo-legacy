package framePackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import mapTiles.MapTile;
import staticPackage.ObjectMap;

public class DrawMapTile extends JPanel implements MouseListener, ActionListener {
	private static final long serialVersionUID = -8785925966340775096L;
	private MapTile[][] map;
	private ObjectMap objectMap;
	private ArrayList<ActionListener> listener = null;
	private MapTile mapTile;
	@SuppressWarnings("unused")
	private MainJFrame mainJFrame;
	private DrawMap drawMap;
	private Color color = Color.BLACK;
	private boolean selected = false;
	private boolean hover = false;
	private ActionListener drawMapTileActionListener;
	// Constructor
	public DrawMapTile(ObjectMap objectMap,int xOfTile, int yOfTile, MainJFrame mainJFrame, DrawMap drawMap) {
		this.mainJFrame = mainJFrame;
		this.objectMap = objectMap;
		this.map = objectMap.getMap();
		this.drawMap = drawMap;
		this.mapTile = map[xOfTile][yOfTile];
        switch(mapTile.getType()) {
			case 0:	color = new Color(56, 216, 59);
					break;
			case 1: color = new Color(0,100,0);
					break;
			case 2: color = new Color(10,130,10);
					break;
			case 3: color = new Color(66,147,33);
					break;
			case 20:color = new Color(0,30,255);
					break;
			default:color = Color.BLACK;
					break;
		}
		listener = new ArrayList<ActionListener>();
        addMouseListener(this);
        drawMapTileActionListener = new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent evt) {
        		if(evt.getActionCommand() == "select") {
					try {
						System.out.println(mapTile);
						removeSelectedFromAllTiles(mainJFrame, xOfTile, yOfTile);
						selected = !selected;
						System.out.println(evt.getActionCommand());
						drawMap.repaint();
					} catch (Exception e) {
						System.out.println(e);
					}
        		}
        		if(evt.getActionCommand() == "toggleHover") {
        			try {
        				hover = !hover;
        				mainJFrame.repaint();
        			} catch (Exception e) {
        				System.out.println(e);
        			}
        			
        		}
			}
		};
		this.addActionListener(getDrawMapTileActionListener());
	}
	public void drawMapTile(Graphics g) {
		mapTile.setHeight((double)drawMap.getHeight()/objectMap.getHeight());
		mapTile.setWidth((double)drawMap.getWidth()/objectMap.getWidth());
		g.setColor(color);
		g.fillRect((int)(mapTile.getWidth()*mapTile.getXPos()-1), (int)(mapTile.getHeight()*mapTile.getYPos()), (int)mapTile.getWidth()+1, (int)mapTile.getHeight()+1);
		g.setColor(new Color(0,0,0,40));
		g.drawRect((int)(mapTile.getWidth()*mapTile.getXPos()-1), (int)(mapTile.getHeight()*mapTile.getYPos()), (int)mapTile.getWidth()+1, (int)mapTile.getHeight()+1);
		if(selected) {
			g.setColor(new Color(255,0,0,100));
			g.fillRect((int)(mapTile.getWidth()*mapTile.getXPos()-1), (int)(mapTile.getHeight()*mapTile.getYPos()), (int)mapTile.getWidth()+1, (int)mapTile.getHeight()+1);
		}
		if(hover) {
			g.setColor(new Color(120,100,0,100));
			g.fillRect((int)(mapTile.getWidth()*mapTile.getXPos()-1), (int)(mapTile.getHeight()*mapTile.getYPos()), (int)mapTile.getWidth()+1, (int)mapTile.getHeight()+1);
		}
	}
	public void removeSelectedFromAllTiles(MainJFrame mainJFrame, int xOfTile, int yOfTile) {
		for( int y = 0; y < map[0].length; y++) {
			for( int x = 0; x < map.length; x++) {
				DrawMapTile[][] drawMapTileArray = mainJFrame.getDrawMapTileArray();
				if( x == xOfTile && y == yOfTile) {
					
				} else {
					drawMapTileArray[x][y].turnOffSelected();
				}
			}
		}
	}
	protected void toggleHover() {
		hover = !hover;
	}
	protected void toggleSelected() {
		selected = !selected;
	}
	public void turnOffSelected() {
		selected = false;
	}
	public MapTile getMapTile() {
		return mapTile;
	}
	public ActionListener getDrawMapTileActionListener() {
		return drawMapTileActionListener;
	}
	@Override
	public void mouseClicked(java.awt.event.MouseEvent evt) {
		fireUpdate(new ActionEvent(this, 0, "select"));
	}
	@Override
	public void mouseEntered(java.awt.event.MouseEvent evt) {
		fireUpdate(new ActionEvent(this, 0, "toggleHover"));
	}
	@Override
	public void mouseExited(java.awt.event.MouseEvent evt) {
		fireUpdate(new ActionEvent(this, 0, "toggleHover"));
	}
	@Override
	public void mousePressed(java.awt.event.MouseEvent evt) {
		
	}
	@Override
	public void mouseReleased(java.awt.event.MouseEvent evt) {
		
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
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
	public ArrayList<ActionListener> getListener() {
		return listener;
	}
}