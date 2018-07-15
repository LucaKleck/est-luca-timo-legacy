package framePackage;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.StringWriter;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import info.ResourcesController;
import net.miginfocom.swing.MigLayout;
import staticPackage.ObjectMap;

public class MainJFrame extends JFrame implements MouseListener {
	private static final long serialVersionUID = 1L;
	private DrawMapTile[][] drawMapTileArray;
	private ResourcesController resources;
	private DrawMap drawMap;
	private BuyMenu buyMenu;
	private JTextPane textPane;
	private StringWriter infoTextWriter;
	public MainJFrame(ObjectMap objectMap, ResourcesController resources) {
		super();
		setMinimumSize(new Dimension(800, 600));
		this.setTitle("The Game");
		this.setSize(555,578);
		this.resources = resources;
		this.infoTextWriter = new StringWriter();
		getContentPane().setLayout(new MigLayout("insets 0 0 0 0, gap 0px 0px", "[80%][20%,grow]", "[75%][25%,grow]"));
		getContentPane().setBackground(new Color(192, 192, 192));
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		try {
			textPane.write(infoTextWriter);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		drawMapTileArray = new DrawMapTile[objectMap.getHeight()][objectMap.getWidth()];
		drawMap = new DrawMap(objectMap,this);
		drawMap.setBorder(new LineBorder(new Color(0, 0, 0)));
		drawMap.addMouseListener(this);
		
		buyMenu = new BuyMenu(this);
		buyMenu.setAlignmentX(Component.RIGHT_ALIGNMENT);
		buyMenu.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		
		getContentPane().add(drawMap, "cell 0 0 1 2,grow");
		getContentPane().add(buyMenu, "flowx,cell 1 0,grow");
		getContentPane().add(textPane, "cell 1 1,grow");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addMouseListener(this);
	}
	public MainJFrame getMainJFrame() {
		return this;
	}
	public StringWriter getTextPaneWriter() {
		return infoTextWriter;
	}
	public DrawMapTile[][] getDrawMapTileArray() {
		return drawMapTileArray;
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
			drawMapTileArray[0][0].removeSelectedFromAllTiles(getMainJFrame());
		}
	}
}
