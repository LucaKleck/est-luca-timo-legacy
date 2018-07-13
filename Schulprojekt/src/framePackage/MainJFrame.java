package framePackage;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import info.ResourcesController;
import staticPackage.ObjectMap;

public class MainJFrame extends JFrame implements MouseListener {
	private static final long serialVersionUID = 1L;
	private DrawMapTile[][] drawMapTile;
	private ResourcesController resources;
	private DrawMap drawMap;
	private BuyMenu buyMenu;
	public MainJFrame(ObjectMap objectMap, ResourcesController resources) {
		drawMapTile = new DrawMapTile[objectMap.getHeight()][objectMap.getWidth()];
		this.setTitle("The Game");
		this.setSize(555,578);
		this.resources = resources;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		drawMap = new DrawMap(objectMap,this);
		getContentPane().add(drawMap, BorderLayout.CENTER);
		buyMenu = new BuyMenu(this);
		getContentPane().add(buyMenu, BorderLayout.EAST);;
		this.setVisible(true);
		drawMap.addMouseListener(this);
		this.addMouseListener(this);
	}
	public MainJFrame getMainJFrame() {
		return this;
	}
	public DrawMapTile[][] getDrawMapTileArray() {
		return drawMapTile;
	}
	public BuyMenu getBuyMenu() {
		return buyMenu;
	}
	public ResourcesController getResources() {
		return resources;
	}
	@Override
	public void mouseClicked(MouseEvent evt) {
		if(evt.getButton() == 3) {
			buyMenu.deselect();
			drawMapTile[0][0].removeSelectedFromAllTiles(getMainJFrame());
		}
	}
	@Override
	public void mouseEntered(MouseEvent evt) {
		
	}
	@Override
	public void mouseExited(MouseEvent evt) {
		
	}
	@Override
	public void mousePressed(MouseEvent evt) {
		
	}
	@Override
	public void mouseReleased(MouseEvent evt) {
		
	}
}
