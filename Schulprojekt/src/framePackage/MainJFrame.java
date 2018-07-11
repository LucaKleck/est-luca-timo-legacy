package framePackage;
import javax.swing.JFrame;

import staticPackage.ObjectMap;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;

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
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.EAST);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(this.getWidth()/objectMap.getWidth()*2);
		panel.add(horizontalStrut_1);
		
		JButton btnTest = new JButton("Test");
		panel.add(btnTest);
		
		Component horizontalStrut = Box.createHorizontalStrut(this.getWidth()/objectMap.getWidth()*2);
		panel.add(horizontalStrut);
		this.setVisible(true);
	}
	public DrawMapTile[][] getDrawMapTileArray() {
		return drawMapTile;
	}
}
