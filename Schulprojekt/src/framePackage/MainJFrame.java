package framePackage;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import framePackageSelectedMenu.SelectedMenuFishingDock;
import framePackageSelectedMenu.SelectedMenuLumbercamp;
import framePackageSelectedMenu.SelectedMenuTownHall;
import gameCore.GameCoreController;
import gameCore.ObjectMap;
import gameCore.ResourceController;
import net.miginfocom.swing.MigLayout;

public class MainJFrame extends JFrame implements MouseListener, ActionListener, MouseWheelListener, ComponentListener {
	private static final long serialVersionUID = 1L;
	private MainJFrame self;
	private DrawMapTile[][] drawMapTileArray;
	private DrawMap drawMap;
	private ResourceController resources = GameCoreController.resourceController;
	private ObjectMap objectMap = GameCoreController.objectMap;
	private BuyMenuTownBuildings buyMenuTownBuildings;
	private LogTextPane logTextPane;
	private JTextPane infoTextPane;
	private JScrollPane scrollPane;
	private JTabbedPane tabbedPlayerInteractionPane;
	private CreateTownHallPanel createTownHallPanel;
	private JButton btnNextTurn;
	private JPanel resourceDisplayPanel;
	private ResourceTextPane resourceText; // TODO make this it's own text panel that refreshes every few seconds or so, no need to update it manually every time.
	
	private Timer recalculateTimer = new Timer( 20, new resizeListener() );
	private class resizeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			drawMap.repaintMapImage();
		}
		
	}
	private class GameFPS implements Runnable {
		private int fps = 0;
		
		public void setFPS(int fps) {
			this.fps = fps;
		}
		public int getFPS() {
			return fps;
		}
		@Override
		public void run() {
				
			java.util.Timer timer = new java.util.Timer(true);
			timer.scheduleAtFixedRate(new Repaint(), 0, 10);
			java.util.Timer fpsTimer = new java.util.Timer(true);
			fpsTimer.scheduleAtFixedRate(new FPS(), 1000, 1000);
			
		}
		private class Repaint extends TimerTask {
			@Override
			public void run() {
				self.repaint();
				setFPS(getFPS()+1);
			}
		}
		private class FPS extends TimerTask {
			@Override
			public void run() {
				System.out.println(getFPS());
				setFPS(0);
			}
		}
	}
	private class MyDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
//            	System.out.println(e.getKeyCode()+" key:"+e.getKeyChar());
                switch(e.getKeyCode()) {
                	case 40:
	                	if(e.isControlDown()) {
	                		drawMap.setYDisplacement(drawMap.getYDisplacement()-50);
	                	} else {
	                		drawMap.setYDisplacement(drawMap.getYDisplacement()-10);
	                	}
                	break;
                	case 39:
                		if(e.isControlDown()) {
                			drawMap.setXDisplacement(drawMap.getXDisplacement()-50);
                		} else {
                			drawMap.setXDisplacement(drawMap.getXDisplacement()-10);
                		}
                	break;
                	case 38:
            			if(e.isControlDown()) {
            				drawMap.setYDisplacement(drawMap.getYDisplacement()+50);
            			} else {
            				drawMap.setYDisplacement(drawMap.getYDisplacement()+10);
            			}
                	break;
                	case 37:
                		if(e.isControlDown()) {
                			drawMap.setXDisplacement(drawMap.getXDisplacement()+50);
                		} else {
                			drawMap.setXDisplacement(drawMap.getXDisplacement()+10);
                		}
                			break;    
                	case 8: drawMap.setXDisplacement(0);
                			drawMap.setYDisplacement(0);
                			drawMap.setDisplacementMultiplier(1);
                			drawMap.repaintMapImage();
                			break;
            	}
            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
                
            } else if (e.getID() == KeyEvent.KEY_TYPED) {
                
            }
            return false;
        }
    }
	public MainJFrame() {
		this.self = this;
		recalculateTimer.setRepeats( false );
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());
		setMinimumSize(new Dimension(600, 600));
		this.setTitle("The Game");
		this.setSize(600,600);
		getContentPane().setLayout(new MigLayout("insets 0 0 0 0, gap 0px 0px", "[70%:70%:70%,grow][30%:30%:30%,grow]", "[5%:5%:5%,grow][75%:75%:75%][5%:5%:5%][20%:20%:20%,grow]"));
		getContentPane().setBackground(new Color(192, 192, 192));
		
		infoTextPane = new JTextPane();
		infoTextPane.setEditable(false);
		
		drawMapTileArray = new DrawMapTile[objectMap.getHeight()][objectMap.getWidth()];
		drawMap = new DrawMap(self);
		drawMap.setBorder(new LineBorder(new Color(0, 0, 0)));
		drawMap.addMouseListener(this);
		
		resourceDisplayPanel = new JPanel();
		getContentPane().add(resourceDisplayPanel, "cell 0 0 2 1,grow");
		resourceDisplayPanel.setLayout(new MigLayout("insets 0 0 0 0, gap 0px 0px", "[20%,grow]", "[100%,grow]"));
		
		resourceText = new ResourceTextPane();
		resourceText.setEditable(false);
		resourceDisplayPanel.add(resourceText, "cell 0 0,grow");
		resourceText.setText(resources.toString());
		
		getContentPane().add(drawMap, "cell 0 1,grow");
		tabbedPlayerInteractionPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPlayerInteractionPane, "flowy,cell 1 1,grow");
		
		createTownHallPanel = new CreateTownHallPanel(this);
		tabbedPlayerInteractionPane.addTab("Town Hall", null, createTownHallPanel, null);
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

		GameFPS gameFPS = new GameFPS();
		gameFPS.run();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addComponentListener(this);
		this.addMouseListener(this);
		this.addMouseWheelListener(this);
		this.setVisible(true);
		
	}
	public void redoDrawMapTile() {
		drawMap.refreshMapImage();
	}
	/*
	 * 
	 */
	public void enableBuyMenuBuildings() {
		tabbedPlayerInteractionPane.removeAll();
		tabbedPlayerInteractionPane.addTab("Buildings", null, buyMenuTownBuildings, null);
		tabbedPlayerInteractionPane.setEnabledAt(0, true);
	}
	public void enableSelectedMenuTownHall() {
		tabbedPlayerInteractionPane.removeAll();
		tabbedPlayerInteractionPane.addTab("Town Hall", null, new SelectedMenuTownHall(self), null);
		tabbedPlayerInteractionPane.setEnabledAt(0, true);
	}
	public void enableSelectedMenuLumbercamp() {
		tabbedPlayerInteractionPane.removeAll();
		tabbedPlayerInteractionPane.addTab("Lumbercamp", null, new SelectedMenuLumbercamp(self), null);
		tabbedPlayerInteractionPane.setEnabledAt(0, true);
	}
	public void enableSelectedMenuFishingDock() {
		tabbedPlayerInteractionPane.removeAll();
		tabbedPlayerInteractionPane.addTab("Fishing Dock", null, new SelectedMenuFishingDock(self), null);
		tabbedPlayerInteractionPane.setEnabledAt(0, true);
	}
	/*
	 * Events
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
			} else {
				drawMapTileArray[0][0].removeSelectedFromAllTiles(self);
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		GameCoreController.GCH.sendCommand(evt.getActionCommand(), self);
	}
	/*
	 * 
	 * Getter
	 * 
	 */
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
	public BuyMenuTownBuildings getBuyMenu() {
		return buyMenuTownBuildings;
	}
	public CreateTownHallPanel getCreateTownHallPanel() {
		return createTownHallPanel;
	}
	/*
	 * 
	 * setter
	 * 
	 */
	/*
	 * 
	 * Motion
	 * 
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent evt) {
		if(evt.getWheelRotation() < 0) {
			if(drawMap.getDisplacementMultiplier() < 2) {				
				drawMap.setDisplacementMultiplier(drawMap.getDisplacementMultiplier()+0.1);
			}
		}
		if(evt.getWheelRotation() > 0) {
			if(drawMap.getDisplacementMultiplier() > 1) {
				drawMap.setDisplacementMultiplier(drawMap.getDisplacementMultiplier()-0.1);
			}
		}
	}
	@Override
	public void componentHidden(ComponentEvent e) {
		
	}
	@Override
	public void componentMoved(ComponentEvent e) {
		
	}
	@Override
	public void componentResized(ComponentEvent e) {
		if ( recalculateTimer.isRunning() ){
		    recalculateTimer.restart();
		  } else {
		    recalculateTimer.start();
		  }
	}
	@Override
	public void componentShown(ComponentEvent e) {
		
	}
}
