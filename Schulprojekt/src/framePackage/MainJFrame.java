package framePackage;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.border.LineBorder;

import info.ResourcesController;
import staticPackage.ObjectMap;

public class MainJFrame extends JFrame implements MouseListener {
	private static final long serialVersionUID = 1L;
	private DrawMapTile[][] drawMapTile;
	private ResourcesController resources;
	private DrawMap drawMap;
	private BuyMenu buyMenu;
	public MainJFrame(ObjectMap objectMap, ResourcesController resources) {
		super();
		getContentPane().setBackground(new Color(192, 192, 192));
		drawMapTile = new DrawMapTile[objectMap.getHeight()][objectMap.getWidth()];
		this.setTitle("The Game");
		this.setSize(555,578);
		this.resources = resources;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{407, 114, 0};
		gridBagLayout.rowHeights = new int[]{525, 0};
		gridBagLayout.columnWeights = new double[]{100.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		drawMap = new DrawMap(objectMap,this);
		drawMap.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_drawMap = new GridBagConstraints();
		gbc_drawMap.fill = GridBagConstraints.BOTH;
		gbc_drawMap.gridx = 0;
		gbc_drawMap.gridy = 0;
		getContentPane().add(drawMap, gbc_drawMap);
		drawMap.addMouseListener(this);
		buyMenu = new BuyMenu(this);
		buyMenu.setAlignmentX(Component.RIGHT_ALIGNMENT);
		buyMenu.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		GridBagConstraints gbc_buyMenu = new GridBagConstraints();
		gbc_buyMenu.anchor = GridBagConstraints.EAST;
		gbc_buyMenu.fill = GridBagConstraints.BOTH;
		gbc_buyMenu.gridx = 1;
		gbc_buyMenu.gridy = 0;
		getContentPane().add(buyMenu, gbc_buyMenu);;
		this.setVisible(true);
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
		if(evt.getButton() == 3) {
			buyMenu.deselect();
			drawMapTile[0][0].removeSelectedFromAllTiles(getMainJFrame());
		}
	}
}
