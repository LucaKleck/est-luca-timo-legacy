package framePackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import gameCore.ObjectMap;

public class DrawMap extends JPanel implements MouseListener, ActionListener, MouseMotionListener {
	private static final long serialVersionUID = 88330795020315231L;
	private DrawMapTile[][] drawMapTile;
	private ObjectMap objectMap;
	private MainJFrame mainJFrame;
	private int xDisplacement = 0;
	private int yDisplacement = 0;
	private double displacementlMultiplier = 1;
	private DrawMap self;
	// constructor
	public DrawMap(ObjectMap objectMap, MainJFrame mainJFrame) {
		super();
		this.self = this;
		this.setBackground(new java.awt.Color(0, 0, 0, 0));
		this.drawMapTile = mainJFrame.getDrawMapTileArray();
		this.mainJFrame = mainJFrame;
		this.objectMap = objectMap;
        addMouseListener(this);
        this.addMouseMotionListener(this);
        setLayout(null);
        for(int yOfTile = 0; yOfTile < objectMap.getHeight(); yOfTile++) {
			for( int xOfTile = 0; xOfTile < objectMap.getWidth(); xOfTile++) {
				drawMapTile[xOfTile][yOfTile] = new DrawMapTile(objectMap,xOfTile,yOfTile,mainJFrame, this);
			}
		}
	}
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, self.getWidth(), self.getHeight());
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
		if(evt.getButton() == 1) { //here the command gets sent into the event handler of this object, if nothing special happened it will only end up sending "select"
			String command=mainJFrame.getCommandHandler().sendCommand("select", self);
			double xPos = ( (evt.getX()-( getDisplacementMultiplier()*getXDisplacement() ) ) / ((double)this.getWidth() /objectMap.getWidth() ) )/getDisplacementMultiplier();
			double yPos = ( (evt.getY()-( getDisplacementMultiplier()*getYDisplacement() ) ) / ((double)this.getHeight()/objectMap.getHeight())	)/getDisplacementMultiplier();
			// ( x - resized displacement) / (width per tile)
			fireUpdate(new ActionEvent(this, 42, command),(int)xPos,(int)yPos);
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
	public int getXDisplacement() {
		return xDisplacement;
	}
	public void setXDisplacement(int xDisplacement) {
		if(xDisplacement > self.getWidth()) {
			xDisplacement = self.getWidth();
		}
		this.xDisplacement = xDisplacement;
	}
	public int getYDisplacement() {
		return yDisplacement;
	}
	public void setYDisplacement(int yDisplacement) {
		if(yDisplacement > self.getHeight()) {
			yDisplacement = self.getHeight();
		}
		this.yDisplacement = yDisplacement;
	}
	public double getDisplacementMultiplier() {
		return displacementlMultiplier;
	}
	public void setDisplacementMultiplier(double displacementMultiplier) {
		this.displacementlMultiplier = displacementMultiplier;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
//		setYDisplacement(getYDisplacement()+e.getY()/(self.getHeight() /3) );
//		setXDisplacement(getXDisplacement()+e.getX()/ (self.getWidth() /3) );
//		repaint();
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
}
