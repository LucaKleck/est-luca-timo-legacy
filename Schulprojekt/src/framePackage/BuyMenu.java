package framePackage;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BuyMenu extends JPanel implements ActionListener {
	private static final long serialVersionUID = 8222232669945381478L;
	private int buyMenuSelected = 0;
	private MainJFrame mainJFrame;
	public BuyMenu(MainJFrame mainJFrame) {
		super();
		this.mainJFrame = mainJFrame;
		JButton button_one = new JButton("button 1");
		//JButton button_two = new JButton("button 2");
		Component horizontalStrut = Box.createHorizontalStrut(20);
		this.add(button_one, BorderLayout.CENTER);
		button_one.addActionListener(this);
		//this.add(button_two);
		this.add(horizontalStrut, BorderLayout.WEST);
	}
	public void paint(Graphics g) {
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		try {
			if(evt.getActionCommand() == "button 1") {
				if(buyMenuSelected != 1) {
					DrawMapTile[][] drawMapTileArray = mainJFrame.getDrawMapTileArray();
					drawMapTileArray[0][0].removeSelectedFromAllTiles(mainJFrame);
					buyMenuSelected = 1;
				} else {
					buyMenuSelected = 0;
				}
					
			}
		System.out.println(buyMenuSelected);
		} catch(Exception e) {
			System.out.println(e);
		}
		
	}
	public int getSelected() {
		return buyMenuSelected;
	}
	public void deselect() {
		this.buyMenuSelected = 0;
		System.out.println(buyMenuSelected);
	}
}
