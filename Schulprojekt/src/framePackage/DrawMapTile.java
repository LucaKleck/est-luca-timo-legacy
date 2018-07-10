package framePackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import mapTiles.MapTile;
import staticPackage.ObjectMap;

public class DrawMapTile extends JPanel implements MouseListener {
	
	private static final long serialVersionUID = 1L;
	private MapTile[][] map;
	@SuppressWarnings("unused")
	private ObjectMap objectMap;
	private MapTile mapTile;
	private Color color = Color.BLACK;
	private boolean selected = false;
	public DrawMapTile(ObjectMap objectMap,int xOfTile, int yOfTile, MainJFrame mainJFrame) {
		this.objectMap = objectMap;
		this.map = objectMap.getMap();
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
        this.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
				try {
					System.out.println(mapTile);
					// remove selected from other tiles except self
					removeSelectedFromAllTiles(mainJFrame, xOfTile, yOfTile);
					selected = !selected;
					mainJFrame.repaint();
				}
				catch (Exception e) {
					System.out.println(e);
				}
			}
		});
	}
	public void paint(Graphics g) {
		double width = (double)this.getWidth();
		double height = (double)this.getHeight();
		mapTile.setHeight(height);
		mapTile.setWidth(width);
		mapTile.drawMapTile(g, color, selected);
		
		
		//drawMapTile(g,map[x][y],color);
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
	public void turnOffSelected() {
		selected = false;
	}
	private ArrayList<ActionListener> listener = null;
	@Override
	public void mouseClicked(java.awt.event.MouseEvent evt) {
		fireUpdate(new ActionEvent(this, 0, "command"));
	}
	@Override
	public void mouseEntered(java.awt.event.MouseEvent evt) {
		
	}
	@Override
	public void mouseExited(java.awt.event.MouseEvent evt) {
		
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
}