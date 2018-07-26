package framePackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;

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
	private DrawnMapImage drawnMapImage;
	private DrawMap self;
	@SuppressWarnings("unused")
	private class DestroyMap implements Runnable {

		@Override
		public void run() {
			Timer refreshTimer = new Timer(true);
			refreshTimer.scheduleAtFixedRate(new RefreshTask(), 100000, 100000);
		}
		private class RefreshTask extends TimerTask {

			@Override
			public void run() {
				System.out.println("DESTROY");
				self.refreshMapImage();
				self.repaint();
			}
			
		}
	}
	// constructor
	public DrawMap(ObjectMap objectMap, MainJFrame mainJFrame) {
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
				drawMapTile[xOfTile][yOfTile].drawMapImage();
			}
//		DestroyMap refreshMap = new DestroyMap();
//		refreshMap.run();
		}
	}
	public void repaintMapImage() {
		drawnMapImage = new DrawnMapImage(self.getWidth(),self.getHeight());
		DrawnMapImage.setG(drawnMapImage.createGraphics());
		for(int yOfTile = 0; yOfTile < objectMap.getHeight(); yOfTile++) {
			for( int xOfTile = 0; xOfTile < objectMap.getWidth(); xOfTile++) {
				drawMapTile[xOfTile][yOfTile].drawMapImage();
			}
		}
	}
	public void refreshMapImage() {
		for(int yOfTile = 0; yOfTile < objectMap.getHeight(); yOfTile++) {
			for( int xOfTile = 0; xOfTile < objectMap.getWidth(); xOfTile++) {
				drawMapTile[xOfTile][yOfTile].drawMapImage();
			}
		}
	}
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, self.getWidth(), self.getHeight());
		if(drawnMapImage == null) {		
			repaintMapImage();
		}
		g.drawImage(drawnMapImage, xDisplacement, yDisplacement, (int) (self.getWidth()*displacementlMultiplier), (int) (self.getHeight()*displacementlMultiplier), mainJFrame);
		super.paint(g);
	}
	public void repaintMapTile(int xOfTile, int yOfTile) {
		drawMapTile[xOfTile][yOfTile].drawMapImage();
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
			// ( x - displacement) / (width per tile) / displacement multiplier
			double xPos = ( (evt.getX() - xDisplacement) / ((double)this.getWidth() /objectMap.getWidth() ) )/getDisplacementMultiplier();
			double yPos = ( (evt.getY() - yDisplacement) / ((double)this.getHeight()/objectMap.getHeight())	)/getDisplacementMultiplier();
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
		if(xDisplacement > 0) {
			xDisplacement = 0;
		}
		if(displacementlMultiplier == 1 && xDisplacement!=0) {
			xDisplacement = 0;
		}
		if(xDisplacement < -self.getWidth()/2*Math.pow(displacementlMultiplier, displacementlMultiplier)/displacementlMultiplier ) {
			xDisplacement = (int) (-self.getWidth()/2*Math.pow(displacementlMultiplier, displacementlMultiplier)/displacementlMultiplier);
		}
		
		this.xDisplacement = xDisplacement;
	}
	public int getYDisplacement() {
		return yDisplacement;
	}
	public void setYDisplacement(int yDisplacement) {
		
		if(yDisplacement > 0) {
			yDisplacement = 0;
		}
		if(displacementlMultiplier == 1 && yDisplacement!=0) {
			yDisplacement = 0;
		}
		if(yDisplacement < -self.getHeight()/2*Math.pow(displacementlMultiplier, displacementlMultiplier)/displacementlMultiplier ) {
			yDisplacement = (int) (-self.getHeight()/2*Math.pow(displacementlMultiplier, displacementlMultiplier)/displacementlMultiplier);
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
		if( (e.getY()-self.getHeight()/2) > 0) {
			setYDisplacement(getYDisplacement()+5);
		} else {
			setYDisplacement(getYDisplacement()-5);
		}
		if( (e.getX()-self.getWidth()/2) > 0) {
			setXDisplacement(getXDisplacement()+5);
		} else {
			setXDisplacement(getXDisplacement()-5);
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
}
