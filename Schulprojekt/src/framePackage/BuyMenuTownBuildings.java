package framePackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

public class BuyMenuTownBuildings extends JPanel implements ActionListener {
	private static final long serialVersionUID = 8222232669945381478L;
	private int buyMenuSelected = 0;
	private MainJFrame mainJFrame;
	private JButton buttonSelectItemOne;
	private JButton buttonSelectItemTwo;
	@SuppressWarnings("unused")
	private BuyMenuTownBuildings self;
	
	public BuyMenuTownBuildings(MainJFrame mainJFrame) {
		this.self = this;
		setBackground(new Color(255, 228, 181));
		setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setLayout(new MigLayout("", "[100%]", "[10%][10%]"));
		this.mainJFrame = mainJFrame;
		
		buttonSelectItemOne = new JButton("Lumbercamp");
		buttonSelectItemOne.setActionCommand("buyItemOne,Building");
		this.add(buttonSelectItemOne, "cell 0 0,grow");
		buttonSelectItemOne.addActionListener(this);
		
		buttonSelectItemTwo = new JButton("Fishing Dock");
		buttonSelectItemTwo.setActionCommand("buyItemTwo,Building");
		this.add(buttonSelectItemTwo, "cell 0 1,grow");
		buttonSelectItemTwo.addActionListener(this);
	}
	public void paint(Graphics g) {
		super.paint(g);
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		try {
			if(evt.getActionCommand().contains("buyItem") && evt.getActionCommand().contains("Building")) { //TODO create a BuyMenuHolder TODO bring this event to there IDEA maybe put it into the GameCommandHandler
				selectItemFromBuyMenuBuildings(evt);
			}
		} catch(Exception e) {
			System.out.println("BMB: "+e);
		}
	}
	@SuppressWarnings("deprecation")
	private void selectItemFromBuyMenuBuildings(ActionEvent evt) {
		if(evt.getActionCommand().contains("One")) {
			if(buyMenuSelected != 1) {
				mainJFrame.getInfoTextPane().setText("Selected "+buttonSelectItemOne.getLabel());//TODO change setText to appear less often (array or something)
				DrawMapTile[][] drawMapTileArray = mainJFrame.getDrawMapTileArray();
				try {
					drawMapTileArray[0][0].removeSelectedFromAllTiles(mainJFrame);
				} catch(IndexOutOfBoundsException e){}
				buyMenuSelected = 1;
			} else {
				buyMenuSelected = 0;
				mainJFrame.getInfoTextPane().setText("Deselected "+buttonSelectItemOne.getLabel());
			}
		}
		if(evt.getActionCommand().contains("Two")) {
			if(buyMenuSelected != 2) {
				mainJFrame.getInfoTextPane().setText("Selected "+buttonSelectItemTwo.getLabel());
				DrawMapTile[][] drawMapTileArray = mainJFrame.getDrawMapTileArray();
				try {
					drawMapTileArray[0][0].removeSelectedFromAllTiles(mainJFrame);
				} catch(IndexOutOfBoundsException e){}
				buyMenuSelected = 2;
			} else {
				buyMenuSelected = 0;
				mainJFrame.getInfoTextPane().setText("Deselected "+buttonSelectItemTwo.getLabel());
			}
		}
	}
	protected int getBuyMenuSelected() {
		return buyMenuSelected;
	}
	protected JButton getButtonSelectedItemOne() {
		return buttonSelectItemOne;
	}
	protected JButton getButtonSelectedItemTwo() {
		return buttonSelectItemTwo;
	}
	protected void setBuyMenuSelected(int selected) {
		this.buyMenuSelected = selected;
	}
	public int getSelected() {
		return buyMenuSelected;
	}
	public void deselect() {
		this.buyMenuSelected = 0;
	}
}
