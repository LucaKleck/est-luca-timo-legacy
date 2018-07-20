package framePackage;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import gameCore.ObjectMap;

public class DrawMap extends JPanel implements MouseListener, ActionListener {
	private static final long serialVersionUID = 88330795020315231L;
	private DrawMapTile[][] drawMapTile;
	private ObjectMap objectMap;
	private MainJFrame mainJFrame;
	// constructor
	public DrawMap(ObjectMap objectMap, MainJFrame mainJFrame) {
		super();
		drawMapTile = mainJFrame.getDrawMapTileArray();
		this.mainJFrame = mainJFrame;
		this.objectMap = objectMap;
		this.setBackground(new java.awt.Color(0, 0, 0, 0));
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
		super.paint(g);
	}
	public void repaintMapTile(int xOfTile, int yOfTile) {
		drawMapTile[xOfTile][yOfTile].drawMapTile(getGraphics());
	}
	@Override
	public void mouseClicked(java.awt.event.MouseEvent evt) {
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
	@Override //This fires update if mouse button 1 was clicked, update is decided by what kind of things are going on
	public void mouseReleased(java.awt.event.MouseEvent evt) {
		String command = "select";
		try {
			if(mainJFrame.getTownHallPanel().getSelected()) { // here buy towns hall is selected as command because towns hall button is selected
				command = "buyTownHall,Building";				
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			if(mainJFrame.getBuyMenu().getSelected() != 0) {
				switch(mainJFrame.getBuyMenu().getSelected()) {
					case 1: command = "buyItemOne,Building";
							break;
					default:command = "ErrorWhileBuying";
							break;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		if(evt.getButton() == 1) { //here the command gets sent into the event handler of this object, if nothing special happened it will only end up sending "select"
			fireUpdate(new ActionEvent(this, 42, command),(int)(evt.getX()/((double)this.getWidth()/objectMap.getWidth())),(int)(evt.getY()/((double)this.getHeight()/objectMap.getHeight())));
		}
	}
	protected void fireUpdate(ActionEvent evt, int xOfTile, int yOfTile) {   // this is the method to send it to the DrawMapTile that was clicked
		try {			
			drawMapTile[xOfTile][yOfTile].fireUpdate(evt);
		} catch(IndexOutOfBoundsException e) {
			
		}
        // note that it goes to DrawMapTile!
    }
    public void addActionListener(ActionListener al, int xOfTile, int yOfTile) {
        drawMapTile[xOfTile][yOfTile].getListener().add(al);
    }
    public void removeActionListener(ActionListener al, int xOfTile, int yOfTile) {
    	drawMapTile[xOfTile][yOfTile].getListener().remove(al);
    }
    public DrawMapTile getDrawMapTile(int xPos, int yPos) {
    	return drawMapTile[xPos][yPos];
    }
	@Override
	public void actionPerformed(ActionEvent evt) {
		
	}
	
}
