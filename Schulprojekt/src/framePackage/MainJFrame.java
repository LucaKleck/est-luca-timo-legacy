package framePackage;
import java.awt.BorderLayout;

import javax.swing.JFrame;

import staticPackage.ObjectMap;

public class MainJFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private DrawMapTile[][] drawMapTile;
	public MainJFrame(ObjectMap objectMap) {
		drawMapTile = new DrawMapTile[objectMap.getHeight()][objectMap.getWidth()];
		this.setTitle("Map");
		this.setSize(555,578);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DrawMap drawMap = new DrawMap(objectMap,this);
		getContentPane().add(drawMap, BorderLayout.CENTER);
		
		BuyMenu buyMenu = new BuyMenu();
		getContentPane().add(buyMenu, BorderLayout.EAST);;
		
		this.setVisible(true);
	}
	public DrawMapTile[][] getDrawMapTileArray() {
		return drawMapTile;
	}
}
