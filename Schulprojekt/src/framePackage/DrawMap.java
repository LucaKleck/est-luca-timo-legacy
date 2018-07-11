package framePackage;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import staticPackage.ObjectMap;

public class DrawMap extends JPanel implements MouseListener, ActionListener {
	private static final long serialVersionUID = 88330795020315231L;
	private DrawMapTile[][] drawMapTile;
	private ObjectMap objectMap;
	// constructor
	public DrawMap(ObjectMap objectMap, MainJFrame mainJFrame) {
		drawMapTile = mainJFrame.getDrawMapTileArray();
		this.objectMap = objectMap;
        addMouseListener(this);
        for(int yOfTile = 0; yOfTile < objectMap.getHeight(); yOfTile++) {
			for( int xOfTile = 0; xOfTile < objectMap.getWidth(); xOfTile++) {
				drawMapTile[xOfTile][yOfTile] = new DrawMapTile(objectMap,xOfTile,yOfTile,mainJFrame, this);
			}
		}
	}
	public void paint(Graphics g) {
		for(int yOfTile = 0; yOfTile < objectMap.getHeight(); yOfTile++) {
			for( int xOfTile = 0; xOfTile < objectMap.getWidth(); xOfTile++) {				
				drawMapTile[xOfTile][yOfTile].drawMapTile(g);
			}
		}
	}
	public void repaintMapTile(int xOfTile, int yOfTile) {
		drawMapTile[xOfTile][yOfTile].drawMapTile(getGraphics());
	}
	@Override
	public void mouseClicked(java.awt.event.MouseEvent evt) {
		fireUpdate(new ActionEvent(this, 0, "select"),(int)(evt.getX()/((double)this.getWidth()/objectMap.getWidth())),(int)(evt.getY()/((double)this.getHeight()/objectMap.getHeight())));
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
	protected void fireUpdate(ActionEvent evt, int xOfTile, int yOfTile) {   
        drawMapTile[xOfTile][yOfTile].fireUpdate(evt);
    }
    public void addActionListener(ActionListener al, int xOfTile, int yOfTile) {
        drawMapTile[xOfTile][yOfTile].getListener().add(al);
    }
    public void removeActionListener(ActionListener al, int xOfTile, int yOfTile) {
    	drawMapTile[xOfTile][yOfTile].getListener().remove(al);
    }
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
}
