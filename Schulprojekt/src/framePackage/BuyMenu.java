package framePackage;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BuyMenu extends JPanel {
	private static final long serialVersionUID = 8222232669945381478L;
	public BuyMenu() {
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		this.add(horizontalStrut_1);
		JButton btnTest = new JButton("Test");
		this.add(btnTest);
		Component horizontalStrut = Box.createHorizontalStrut(20);
		this.add(horizontalStrut);
	}
}
