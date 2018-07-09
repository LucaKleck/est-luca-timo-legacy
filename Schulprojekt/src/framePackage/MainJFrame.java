package framePackage;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import staticPackage.ObjectMap;

public class MainJFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private DrawMapTile[][] drawMapTile;
	public MainJFrame(ObjectMap objectMap) {
		drawMapTile = new DrawMapTile[objectMap.getHeight()][objectMap.getWidth()];
		this.setTitle("Map");
		this.setSize(555,578);
		getContentPane().setLayout(new GridLayout( objectMap.getHeight(), objectMap.getWidth() ));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for(int y = 0; y < objectMap.getHeight(); y++) {
			for( int x = 0; x < objectMap.getWidth(); x++) {
				drawMapTile[x][y] = new DrawMapTile(objectMap,x,y,this);
				getContentPane().add(drawMapTile[x][y]);
			}
		}
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		
	}
	
}
