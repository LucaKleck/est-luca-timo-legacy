package framePackage;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import info.ResourcesController;
import net.miginfocom.swing.MigLayout;
import staticPackage.ObjectMap;
import javax.swing.JTabbedPane;

public class MainJFrame extends JFrame implements MouseListener {
	private static final long serialVersionUID = 1L;
	private DrawMapTile[][] drawMapTileArray;
	private ResourcesController resources;
	private DrawMap drawMap;
	private BuyMenuBuildings buyMenuBuildings;
	private JTextPane infoTextPane;
	private JScrollPane scrollPane;
	private LogTextPane logTextPane;
	private JTabbedPane tabbedPane;
	public MainJFrame(ObjectMap objectMap, ResourcesController resources) {
		super();
		setMinimumSize(new Dimension(600, 600));
		this.setTitle("The Game");
		this.setSize(600,600);
		this.resources = resources;
		getContentPane().setLayout(new MigLayout("insets 0 0 0 0, gap 0px 0px", "[70%:70%:70%,grow][30%:30%:30%,grow]", "[75%:75%:75%][25%:25%:25%,grow]"));
		getContentPane().setBackground(new Color(192, 192, 192));
		
		infoTextPane = new JTextPane();
		infoTextPane.setEditable(false);
		
		drawMapTileArray = new DrawMapTile[objectMap.getHeight()][objectMap.getWidth()];
		drawMap = new DrawMap(objectMap,this);
		drawMap.setBorder(new LineBorder(new Color(0, 0, 0)));
		drawMap.addMouseListener(this);
		
		getContentPane().add(drawMap, "cell 0 0,grow");
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, "flowy,cell 1 0,grow");
		
		buyMenuBuildings = new BuyMenuBuildings(this);
		buyMenuBuildings.setName("");
		buyMenuBuildings.setToolTipText("");
		tabbedPane.addTab("Buildings", null, buyMenuBuildings, null);
		tabbedPane.setEnabledAt(0, true);
		buyMenuBuildings.setAlignmentX(Component.RIGHT_ALIGNMENT);
		buyMenuBuildings.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		
		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 0 1,grow");
		
		logTextPane = new LogTextPane();
		logTextPane.setBackground(Color.LIGHT_GRAY);
		logTextPane.setEditable(false);
		scrollPane.setViewportView(logTextPane);
		getContentPane().add(infoTextPane, "cell 1 1,grow");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addMouseListener(this);
	}
	public MainJFrame getMainJFrame() {
		return this;
	}
	public JTextPane getInfoTextPane() {
		return infoTextPane;
	}
	public LogTextPane getLogTextPane() {
		return logTextPane;
	}
	public DrawMapTile[][] getDrawMapTileArray() {
		return drawMapTileArray;
	}
	public BuyMenuBuildings getBuyMenu() {
		return buyMenuBuildings;
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
			buyMenuBuildings.deselect();
			drawMapTileArray[0][0].removeSelectedFromAllTiles(getMainJFrame());
		}
	}
}
