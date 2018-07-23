package framePackage;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import framePackageSelectedMenu.SelectedMenuLumbercamp;
import framePackageSelectedMenu.SelectedMenuTownHall;
import gameCore.CreateMap;
import gameCore.GameCommandHandler;
import gameCore.ObjectMap;
import gameCore.ResourcesController;
import net.miginfocom.swing.MigLayout;

public class MainJFrame extends JFrame implements MouseListener, ActionListener, MouseWheelListener {
	private static final long serialVersionUID = 1L;
	private MainJFrame self;
	private DrawMapTile[][] drawMapTileArray;
	private ResourcesController resources;
	private DrawMap drawMap;
	private BuyMenuTownBuildings buyMenuTownBuildings;
	private JTextPane infoTextPane;
	private JScrollPane scrollPane;
	private LogTextPane logTextPane;
	private JTabbedPane tabbedPlayerInteractionPane;
	private ObjectMap objectMap;
	private CreateTownHallPanel createTownHallPanel;
	private JButton btnNextTurn;
	private GameCommandHandler commandHandler;
	private JPanel resourceDisplayPanel;
	private JTextPane resourceText;
	public static Logger logger;
	
	private class MyDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
//            	System.out.println(e.getKeyCode()+" key:"+e.getKeyChar());
                switch(e.getKeyCode()) {
                	case 40:drawMap.setYDisplacement(drawMap.getYDisplacement()-10);
                			drawMap.repaint();
                			break;
                	case 39:drawMap.setXDisplacement(drawMap.getXDisplacement()-10);
                			drawMap.repaint();
                			break;
                	case 38:drawMap.setYDisplacement(drawMap.getYDisplacement()+10);
                			drawMap.repaint();
                			break;
                	case 37:drawMap.setXDisplacement(drawMap.getXDisplacement()+10);
                			drawMap.repaint();
                			break;    
                	case 8: drawMap.setXDisplacement(0);
                			drawMap.setYDisplacement(0);
                			drawMap.setDisplacementMultiplier(1);
                			drawMap.repaint();
                			break;
            	}
            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
                
            } else if (e.getID() == KeyEvent.KEY_TYPED) {
                
            }
            return false;
        }
    }
	public MainJFrame(Logger logger) {
		MainJFrame.setLogger(logger);
		CreateMap.setLogger(logger);
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());
		this.self = this;
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
		
		createTownHallPanel = new CreateTownHallPanel(this);
		tabbedPlayerInteractionPane.addTab("Towns Hall", null, createTownHallPanel, null);
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
		this.addMouseListener(this);
		this.addMouseWheelListener(this);
		this.setVisible(true);
	}
	public void redoDrawMapTile() {
		drawMapTileArray = new DrawMapTile[objectMap.getHeight()][objectMap.getWidth()];
		drawMap = new DrawMap(objectMap,this);
		drawMap.setBorder(new LineBorder(new Color(0, 0, 0)));
		drawMap.addMouseListener(this);
		getContentPane().add(drawMap, "cell 0 1,grow");
		repaint();
	}
	/*
	 * 
	 */
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
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
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
				drawMapTileArray[0][0].removeSelectedFromAllTiles(self);
				enableBuyMenuBuildings();				
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		commandHandler.sendCommand(evt.getActionCommand(), self);
	}
	/*
	 * 
	 * Getter
	 * 
	 */
	public static Logger getLogger() {
		return logger;
	}
	public JTextPane getResourceText() {
		return resourceText;
	}
	public GameCommandHandler getCommandHandler() {
		return commandHandler;
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
	public CreateTownHallPanel getCreateTownHallPanel() {
		return createTownHallPanel;
	}
	/*
	 * 
	 * setter
	 * 
	 */
	private static void setLogger(Logger logger) {
		MainJFrame.logger = logger;
	}
	/*
	 * 
	 * Motion
	 * 
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent evt) {
		if(evt.getWheelRotation() < 0) {
			if(drawMap.getDisplacementMultiplier() < 5) {				
				drawMap.setDisplacementMultiplier(drawMap.getDisplacementMultiplier()+0.1);
				drawMap.repaint();
			}
		}
		if(evt.getWheelRotation() > 0) {
			if(drawMap.getDisplacementMultiplier() > 1) {
				drawMap.setDisplacementMultiplier(drawMap.getDisplacementMultiplier()-0.1);
				drawMap.repaint();
			}
		}
	}
}
