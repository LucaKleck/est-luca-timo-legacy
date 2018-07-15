package framePackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

public class BuyMenu extends JPanel implements ActionListener {
	private static final long serialVersionUID = 8222232669945381478L;
	private int buyMenuSelected = 0;
	private MainJFrame mainJFrame;
	public BuyMenu(MainJFrame mainJFrame) {
		super();
		setBackground(new Color(255, 228, 181));
		setBorder(new LineBorder(new Color(0, 0, 0), 2));
		this.mainJFrame = mainJFrame;
		JButton button_one = new JButton("item one");
		setLayout(new MigLayout("", "[90%]", "[10%][10%]"));
		this.add(button_one, "cell 0 0,grow");
		button_one.addActionListener(this);
		JButton button_two = new JButton("button 2");
		this.add(button_two, "cell 0 1,grow");
	}
	public void paint(Graphics g) {
		super.paint(g);
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		try {
			if(evt.getActionCommand() == "item one") {
				if(buyMenuSelected != 1) {
					mainJFrame.getTextPaneWriter().write("Selected "+evt.getActionCommand());
					mainJFrame.getTextPaneWriter().flush();
					
					DrawMapTile[][] drawMapTileArray = mainJFrame.getDrawMapTileArray();
					drawMapTileArray[0][0].removeSelectedFromAllTiles(mainJFrame);
					buyMenuSelected = 1;
				} else {
					buyMenuSelected = 0;
					mainJFrame.getTextPaneWriter().write("deselected");
				}
					
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
	}
	public int getSelected() {
		return buyMenuSelected;
	}
	public void deselect() {
		this.buyMenuSelected = 0;
		mainJFrame.getTextPaneWriter().write("deselected");
	}
}
