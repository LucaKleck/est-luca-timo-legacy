package gameCore;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

import buildings.Building;
import buildings.FishingDock;
import buildings.Lumbercamp;
import buildings.TownHall;
import framePackage.CreateTownHallPanel;
import framePackage.DrawMap;
import framePackage.DrawMapTile;
import framePackage.LogTextPane;
import framePackage.MainJFrame;
import info.Item;
import info.Resource;
import mapTiles.MapTile;
import mapTiles.MapTileWithResources;

public class GameCommandHandler implements ActionListener {
	private MainJFrame mainJFrame = GameCoreController.mainJFrame;
	private ObjectMap objectMap = GameCoreController.objectMap;
	private Logger log = GameCoreController.log;
	private ResourceController resourceController = GameCoreController.resourceController;
	private MapTile[][] map;
	private JTextPane infoTextPane; 
	private LogTextPane logTextPane;
	private JTabbedPane tabbedPane;
	private DrawMapTile[][] drawMapTileArray;
	private DrawMap drawMap;
	private CreateTownHallPanel createTownHallPanel;
	
	public GameCommandHandler() {	
		this.map = objectMap.getMap();
		this.createTownHallPanel = mainJFrame.getCreateTownHallPanel();
		this.infoTextPane = mainJFrame.getInfoTextPane();
		this.logTextPane = mainJFrame.getLogTextPane();
		this.tabbedPane = mainJFrame.getTabbedPane();
		this.drawMapTileArray = mainJFrame.getDrawMapTileArray();
		this.drawMap = mainJFrame.getDrawMap();
	}
	// Methods
	
	/*
	 * 
	 * Command handlers
	 * 
	 * 
	 */
	
	/*
	 * Default
	 */
	public String sendCommand(String command, Object source) {
		String result = "ERROR";
		if(source.getClass() == DrawMapTile.class) {
			return result = sendCommand(command, (DrawMapTile)source);			
		}
		if(source.getClass() == DrawMap.class) {
			return result = sendCommand(command, (DrawMap)source);
		}
		return result;
	}
	/*
	 * DrawMap
	 */
	public String sendCommand(String command, DrawMap source) {
		try {
			if(mainJFrame.getCreateTownHallPanel().getSelected()) { // here buy town hall is selected as command because town hall button is selected
				command = "buyTownHall,Building";
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "EXEPTION SEND COMMAND DRAW MAP TOWN HALL: ", e);
		}
		try {
			if(mainJFrame.getBuyMenu().getSelected() != 0) {
				switch(mainJFrame.getBuyMenu().getSelected()) {
					case 1: command = "buyItemOne,Building";
							break;
					case 2: command = "buyItemTwo,Building";
							break;
					default:command = "ErrorWhileBuying";
							break;
				}
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "EXEPTION SEND COMMAND DRAW MAP ITEMS: ", e);
		}
		return command;
	}
	/*
	 * MainJFrame
	 */
	public String sendCommand(String command, MainJFrame source) {
		try {
			if(command.toString() == "townHallToggle") {
				createTownHallPanel.toggleSelected();
				drawMapTileArray[0][0].removeSelectedFromAllTiles(mainJFrame);
				if(createTownHallPanel.getSelected()) this.getInfoTextPane().setText("Selected Town Hall");
				else this.getInfoTextPane().setText("Deselected Town Hall");
			}
			if(command == "NextTurn" && objectMap.hasTownHall()) {
				nextTurn();
			}
		} catch(Exception e) {
			log.log(Level.SEVERE, "MJF: Action Performed: ", e);
		}
		return command;
	}
	/*
	 * DrawMapTile
	 */
	public String sendCommand(String command, DrawMapTile source) {
		String result = "noIssue";
		MapTile mapTile = source.getMapTile();
		if(!command.isEmpty() && command!="select") {
			boolean isBuilding = false;
    		Item item = new Item(command); // This strips the string off of building information and buy
			if(command.contains(",Building")) {
				isBuilding = true;
			}
			// buys item(containing price) on MapTile mapTile, costs the global Resources
			buyItem(item, isBuilding, mapTile);
			//
 			if(command=="buyTownHall,Building") {
 				mainJFrame.getCreateTownHallPanel().toggleSelected();
 			}
			mainJFrame.getBuyMenu().deselect();
			source.toggleSelected();
			drawMap.repaintMapTile(mapTile.getXPos(), mapTile.getYPos());
		}
		if(command == "select") {
			try {	
				mainJFrame.getInfoTextPane().setText(""+mapTile);
				source.toggleSelected();
				source.removeSelectedFromAllTiles(mainJFrame,mapTile.getXPos(),mapTile.getYPos());
			} catch (Exception e) {
				System.out.println("selected "+e);
			}
			if(mapTile.getBuilding() == null && objectMap.hasTownHall()) {
				mainJFrame.enableBuyMenuBuildings();
			}
			try {
				selectMenu(source, mapTile);
			} catch(NullPointerException e) {
				// No building
			}
		}
		if(command == "toggleHover") {
			try {
				source.toggleHover();
			} catch (Exception e) {
				System.out.println("selected "+e);
				result = e.toString();
			}
		}
		drawMap.repaintMapTile(mapTile.getXPos(), mapTile.getYPos());
		return result;
	}
	/*
	 * 
	 * 
	 * Other Methods
	 * 
	 * 
	 */
	private void selectMenu(DrawMapTile source, MapTile mapTile) {
		if(mapTile.getBuilding().getName()=="Town Hall"&&source.getSelected()==true) {
			mainJFrame.enableSelectedMenuTownHall();
		}
		if(mapTile.getBuilding().getName()=="Lumbercamp"&&source.getSelected()==true) {
			mainJFrame.enableSelectedMenuLumbercamp();
		}
		if(mapTile.getBuilding().getName()=="Fishing Dock"&&source.getSelected()==true) {
			mainJFrame.enableSelectedMenuFishingDock();
		}
		if(source.getSelected()==false && objectMap.hasTownHall()) {
			mainJFrame.enableBuyMenuBuildings();
		}
	}
	private void nextTurn() {
		// Here goes all the stuff that will happen
		
		// Need arrays of all things, that means we need unit array!
		// TODO create turn control item, to count rounds and so on
		// TODO calc new resources IDEA maybe upkeep or something?
	}
	private void writeToLogAndSetText(String textToSet) {
		logTextPane.writeToLog(textToSet);		
		infoTextPane.setText(textToSet);
		log.fine(textToSet);
	}
	private void writeToLogAndSetTextBought(Item item, Building building) {
		logTextPane.writeToLog("bought: " + item);
		infoTextPane.setText(building+"\n"+resourceController);
		log.fine("bought: " + item);
		log.fine(building+"\n"+resourceController);
	}
	private void subtracktResources(Item item) {
		Resource[] cost = item.getCost();
		for(int subtrackt = 0; subtrackt < resourceController.getResources().length; subtrackt++) {
			resourceController.getResources()[subtrackt].removeResourceAmount(cost[subtrackt].getResourceAmount());
		}
	}
	/*
	 * 
	 * Buy Methods
	 * 
	 */
	public void buyItem(Item item, boolean isBuilding, MapTile mapTile) {
		try {			
			if(isBuilding) {
				buyBuilding(item,(MapTileWithResources)mapTile);
			}
		} catch (Exception e) {
			System.out.println("Buy item - GameCommandHandler.216~ - "+e);
		}
	} 
	public void buyBuilding(Item item, MapTileWithResources mapTile) {
		if(mapTile.getBuilding() == null) {
			boolean hasResources=false;			
			try {
				hasResources = item.hasResources(resourceController, mapTile);
			} catch (ClassCastException e) {
				
			}
			if(hasResources) {
				Building building = null;
				switch(item.getItemName()) {
					case "Town Hall":	if(mapTile.getMapTileType().getName()!="River") {
											building = new TownHall(mainJFrame, mapTile);
											mainJFrame.enableSelectedMenuTownHall();
										}
										break;
					case "Lumbercamp":		building = new Lumbercamp(mapTile);
											mainJFrame.enableSelectedMenuLumbercamp();
										break;
					case "Fishing Dock":if(mapTile.getMapTileType().getName()=="River") {
											building = new FishingDock(mapTile);
											mainJFrame.enableSelectedMenuFishingDock();
										}
										break;
					default: writeToLogAndSetText("Missing Resources/Building blocked + DEFAULTCASE!!! (not good, something bad happened)"); return;
				}
				if(building != null) {
					subtracktResources(item);
					mapTile.setBuilding(building);
					writeToLogAndSetTextBought(item, building);					
				} else { writeToLogAndSetText("Missing Resources/Invalid Field"); return;}
			} else {
				writeToLogAndSetText("Missing Resources/Invalid Field"); return;
			}
		} else {
			writeToLogAndSetText("Building blocked");	return;
		}
		
	}
	/*
	 * Getter
	 */
	public MainJFrame getMainJFrame() {
		return mainJFrame;
	}
	public ObjectMap getObjectMap() {
		return objectMap;
	}
	public MapTile[][] getMap() {
		return map;
	}
	public JTextPane getInfoTextPane() {
		return infoTextPane;
	}
	public LogTextPane getLogTextPane() {
		return logTextPane;
	}
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
	public DrawMapTile[][] getDrawMapTileArray() {
		return drawMapTileArray;
	}
	public DrawMap getDrawMap() {
		return drawMap;
	}
	public void actionPerformed(ActionEvent evt) {
	}	
}