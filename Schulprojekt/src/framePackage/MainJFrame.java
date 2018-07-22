package framePackage;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import framePackageSelectedMenu.SelectedMenuLumbercamp;
import framePackageSelectedMenu.SelectedMenuTownHall;
import gameCore.GameCommandHandler;
import gameCore.ObjectMap;
import gameCore.ResourcesController;
import mapTiles.MapTile;
import net.miginfocom.swing.MigLayout;
import javax.swing.JPanel;

public class MainJFrame extends JFrame implements MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private DrawMapTile[][] drawMapTileArray;
	private ResourcesController resources;
	private DrawMap drawMap;
	private BuyMenuTownBuildings buyMenuTownBuildings;
	private JTextPane infoTextPane;
	private JScrollPane scrollPane;
	private LogTextPane logTextPane;
	private JTabbedPane tabbedPlayerInteractionPane;
	private ObjectMap objectMap;
	private CreateTownHallPanel townsHallPanel;
	private JButton btnNextTurn;
	private GameCommandHandler commandHandler;
	private JPanel resourceDisplayPanel;
	private JTextPane resourceText;
	
	public MainJFrame() {
		this.objectMap = new ObjectMap();
		this.resources = new ResourcesController();
		setMinimumSize(new Dimension(600, 600));
		this.setTitle("The Game");
		this.setSize(600,600);
		getContentPane().setLayout(new MigLayout("insets 0 0 0 0, gap 0px 0px", "[70%:70%:70%,grow][30%:30%:30%,grow]", "[5%:5%:5%,grow][75%:75%:75%][5%:5%:5%][20%:20%:20%,grow]"));
		getContentPane().setBackground(new Color(192, 192, 192));
		
		infoTextPane = new JTextPane();
		infoTextPane.setEditable(false);
		
		drawMapTileArray = new DrawMapTile[objectMap.getHeight()][objectMap.getWidth()];
		drawMap = new DrawMap(objectMap,this);
		drawMap.setBorder(new LineBorder(new Color(0, 0, 0)));
		drawMap.addMouseListener(this);
		
		resourceDisplayPanel = new JPanel();
		getContentPane().add(resourceDisplayPanel, "cell 0 0 2 1,grow");
		resourceDisplayPanel.setLayout(new MigLayout("insets 0 0 0 0, gap 0px 0px", "[20%,grow]", "[100%,grow]"));
		
		resourceText = new JTextPane();
		resourceText.setEditable(false);
		resourceDisplayPanel.add(resourceText, "cell 0 0,grow");
		resourceText.setText(resources.toString());
		
		getContentPane().add(drawMap, "cell 0 1,grow");
		
		tabbedPlayerInteractionPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPlayerInteractionPane, "flowy,cell 1 1,grow");
		
		townsHallPanel = new CreateTownHallPanel(this);
		tabbedPlayerInteractionPane.addTab("Towns Hall", null, townsHallPanel, null);
		tabbedPlayerInteractionPane.setEnabledAt(0, true);
		
		buyMenuTownBuildings = new BuyMenuTownBuildings(this);
		buyMenuTownBuildings.setName("");
		buyMenuTownBuildings.setToolTipText("Here you can buy Buildings");
		buyMenuTownBuildings.setAlignmentX(Component.RIGHT_ALIGNMENT);
		buyMenuTownBuildings.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		
		btnNextTurn = new JButton("Next Turn");
		btnNextTurn.setActionCommand("NextTurn");
		btnNextTurn.setToolTipText("Ends current turn and progresses the game");
		btnNextTurn.addActionListener(this);
		
		getContentPane().add(btnNextTurn, "cell 1 2,grow");
		
		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 0 2 1 2,grow");
		
		logTextPane = new LogTextPane();
		logTextPane.setBackground(Color.LIGHT_GRAY);
		logTextPane.setEditable(false);
		scrollPane.setViewportView(logTextPane);
		getContentPane().add(infoTextPane, "cell 1 3,grow");
		
		commandHandler = new GameCommandHandler(this);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addMouseListener(this);
	}
	public void redoDrawMapTile() {
		drawMapTileArray = new DrawMapTile[objectMap.getHeight()][objectMap.getWidth()];
		drawMap = new DrawMap(objectMap,this);
		drawMap.setBorder(new LineBorder(new Color(0, 0, 0)));
		drawMap.addMouseListener(this);
		getContentPane().add(drawMap, "cell 0 1,grow");
		repaint();
	}
	public void enableBuyMenuBuildings() {
		tabbedPlayerInteractionPane.removeTabAt(0);
		tabbedPlayerInteractionPane.addTab("Buildings", null, buyMenuTownBuildings, null);
		tabbedPlayerInteractionPane.setEnabledAt(0, true);
	}
	public void enableSelectedMenuTownHall() {
		tabbedPlayerInteractionPane.removeTabAt(0);
		tabbedPlayerInteractionPane.addTab("Town Hall", null, new SelectedMenuTownHall(), null);
		tabbedPlayerInteractionPane.setEnabledAt(0, true);
	}
	public void enableSelectedMenuLumbercamp() {
		tabbedPlayerInteractionPane.removeTabAt(0);
		tabbedPlayerInteractionPane.addTab("Lumbercamp", null, new SelectedMenuLumbercamp(), null);
		tabbedPlayerInteractionPane.setEnabledAt(0, true);
	}
	public void testForTownHall() {
		MapTile[][] map = objectMap.getMap();
		for(int y = 0; y < map[0].length; y++) {
			for(int x = 0; x < map.length; x++) {
				try {
					if(map[x][y].getBuilding().getName() == "Town Hall") {
						tabbedPlayerInteractionPane.removeTabAt(0);
						tabbedPlayerInteractionPane.addTab("Buildings", null, buyMenuTownBuildings, null);
						tabbedPlayerInteractionPane.setEnabledAt(0, true);
						buyMenuTownBuildings.setEnabled(true);
						return;
					}
				} catch(NullPointerException e) {
					// just normal null pointer Exceptions due to most tiles not having buildings
				}
			}
		}
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
			if(objectMap.hasTownHall()) {
				buyMenuTownBuildings.deselect();
				drawMapTileArray[0][0].removeSelectedFromAllTiles(getMainJFrame());
				enableBuyMenuBuildings();				
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		try {
			if(evt.getActionCommand().toString() == "townsHallToggle") {
				townsHallPanel.toggleSelected();
				drawMapTileArray[0][0].removeSelectedFromAllTiles(this);
				if(townsHallPanel.getSelected()) this.getInfoTextPane().setText("Selected Towns Hall");
				else this.getInfoTextPane().setText("Deselected Towns Hall");
			}
			if(evt.getActionCommand().toString() == "NextTurn" && objectMap.hasTownHall()) {
				nextTurn();
			}
		} catch(Exception e) {
			System.out.println("MJF: Action Performed: "+e);
		}
	}
	public void nextTurn() {
		// DOIT put this into the CommandHandler
		// Here goes all the stuff that will happen
		// Need arrays of all things, that means we need unit array!
		// TODO create turn control item, to count rounds and so on
		// TODO make Lumbercamps have a resource they create, BuildingsWithResources, abstract
		// TODO calc new resources IDEA maybe upkeep or something?
		System.out.println("TEST");
	}
	public JTextPane getResourceText() {
		return resourceText;
	}
	public GameCommandHandler getCommandHandler() {
		return commandHandler;
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
	public JTabbedPane getTabbedPane() {
		return tabbedPlayerInteractionPane;
	}
	public DrawMapTile[][] getDrawMapTileArray() {
		return drawMapTileArray;
	}
	public DrawMap getDrawMap() {
		return drawMap;
	}
	public ObjectMap getObjectMap() {
		return objectMap;
	}
	public BuyMenuTownBuildings getBuyMenu() {
		return buyMenuTownBuildings;
	}
	public ResourcesController getResources() {
		return resources;
	}
	public CreateTownHallPanel getTownHallPanel() {
		return townsHallPanel;
	}
}
