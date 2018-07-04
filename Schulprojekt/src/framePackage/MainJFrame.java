package framePackage;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.border.LineBorder;

import staticPackage.ObjectMap;

public class MainJFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	public MainJFrame(ObjectMap objectMap) {
		this.setTitle("Map");
		this.setSize(600,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		DrawMap drawMap = new DrawMap(objectMap);
		drawMap.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		getContentPane().add(drawMap);
		DrawGui drawGui = new DrawGui();
		getContentPane().add(drawGui);
		this.setVisible(true);
	}
}
