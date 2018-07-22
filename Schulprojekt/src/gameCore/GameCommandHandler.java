package gameCore;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

import buildings.Building;
import buildings.FishingHutt;
import buildings.Lumbercamp;
import buildings.TownHall;
import framePackage.DrawMap;
import framePackage.DrawMapTile;
import framePackage.LogTextPane;
import framePackage.MainJFrame;
import info.Item;
import info.SingleResourceTypeWithAmount;
import mapTiles.MapTile;
import mapTiles.MapTileWithResources;

public class GameCommandHandler implements ActionListener {
	private MainJFrame mainJFrame;
	private ObjectMap objectMap;
	private ResourcesController resources;
	private MapTile[][] map;
	private JTextPane infoTextPane; 
	private LogTextPane logTextPane;
	private JTabbedPane tabbedPane;
	private DrawMapTile[][] drawMapTileArray;
	private JTextPane resourceText;
	private DrawMap drawMap;
	
	public GameCommandHandler(MainJFrame mainJFrame) {
		this.mainJFrame = mainJFrame;
		this.objectMap = mainJFrame.getObjectMap();
		this.resources = mainJFrame.getResources();
		this.map = objectMap.getMap();
		this.resourceText = mainJFrame.getResourceText();
		this.infoTextPane = mainJFrame.getInfoTextPane();
		this.logTextPane = mainJFrame.getLogTextPane();
		this.tabbedPane = mainJFrame.getTabbedPane();
		this.drawMapTileArray = mainJFrame.getDrawMapTileArray();
		this.drawMap = mainJFrame.getDrawMap();
	}

	// Methods
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
	public String sendCommand(String command, DrawMap source) {
		try {
			if(mainJFrame.getTownHallPanel().getSelected()) { // here buy towns hall is selected as command because towns hall button is selected
				command = "buyTownHall,Building";				
			}
		} catch (Exception e) {
			System.out.println(e);
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
			System.out.println(e);
		}
		return command;
	}
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
			if(command=="buyTownHall,Building") mainJFrame.getTownHallPanel().toggleSelected();mainJFrame.testForTownHall();
			mainJFrame.getBuyMenu().deselect();
			source.toggleSelected();
			drawMap.repaintMapTile(mapTile.getXPos(), mapTile.getYPos());
		}
		if(command == "select") {
			try {	
				mainJFrame.getInfoTextPane().setText(""+mapTile);
				source.toggleSelected();
				source.removeSelectedFromAllTiles(mainJFrame,mapTile.getXPos(),mapTile.getYPos());
				drawMap.repaintMapTile(mapTile.getXPos(), mapTile.getYPos());
			} catch (Exception e) {
				System.out.println("selected "+e);
			}
			if(mapTile.getBuilding() == null && objectMap.hasTownHall()) {
				mainJFrame.enableBuyMenuBuildings();
			}
			try { //TODO bundle to method
				if(mapTile.getBuilding().getName()=="Town Hall"&&source.getSelected()==true) {
					mainJFrame.enableSelectedMenuTownHall();
				}
				if(mapTile.getBuilding().getName()=="Lumbercamp"&&source.getSelected()==true) {
					mainJFrame.enableSelectedMenuLumbercamp();
				}
				if(source.getSelected()==false && objectMap.hasTownHall()) {
					mainJFrame.enableBuyMenuBuildings();
				}
			} catch(NullPointerException e) {
				// No building
			}
		}
		if(command == "toggleHover") {
			try {
				source.toggleHover();
				drawMap.repaintMapTile(mapTile.getXPos(), mapTile.getYPos());
			} catch (Exception e) {
				System.out.println("selected "+e);
			}
		}
		return result;
	}
	public void buyItem(Item item, boolean isBuilding, MapTile mapTile) {
		if(isBuilding) {
			switch(item.getItemName()) {
				case "Town Hall":	buyTownHall(mapTile, item);
									break;
				case "Lumbercamp":	buyLumbercamp(mapTile, item);
									break;
				case "Fishing Hutt":buyFishingHutt(mapTile, item);
									break;
				default: writeToLogAndSetText("Missing Resources/Building blocked + DEFAULTCASE!!! (not good, something bad happened)"); return;
			}
		}
		resourceText.setText(resources.toString());
		mainJFrame.repaint();
	} 
	private void buyFishingHutt(MapTile mapTile, Item item) {
		if(mapTile.getBuilding() == null) {
			boolean hasResources=false;
			try {
				hasResources = item.hasResources(resources, (MapTileWithResources) mapTile);
			} catch (ClassCastException e) {
				
			}
			if(hasResources) {
				Building building = new FishingHutt(mapTile);
				subtracktResources(item);
				mapTile.setBuilding(building);
				writeToLogAndSetTextBought(item, building);
			} else {
				writeToLogAndSetText("Missing Resources/Invalid Field"); return;
			}
		} else {
			writeToLogAndSetText("Missing Resources/Building blocked");	return;
		}
	}
	private void buyLumbercamp(MapTile mapTile, Item item) {
		if(mapTile.getBuilding() == null) {
			boolean hasResources=false;
			try {
				hasResources = item.hasResources(resources, (MapTileWithResources) mapTile);
			} catch (ClassCastException e) {
				
			}
			if(hasResources) {
				Building building = new Lumbercamp(mapTile);
				subtracktResources(item);
				mapTile.setBuilding(building);
				writeToLogAndSetTextBought(item, building);
			} else {
				writeToLogAndSetText("Missing Resources/Invalid Field"); return;
			}
		} else {
			writeToLogAndSetText("Missing Resources/Building blocked");	return;
		}
	}
	private void buyTownHall(MapTile mapTile, Item item) {
		if(mapTile.getBuilding() == null && mapTile.getMapTileType().getType() != 20) {
			Building building = new TownHall(mainJFrame, mapTile);
			subtracktResources(item);
			mapTile.setBuilding(building);
			writeToLogAndSetTextBought(item, mapTile.getBuilding());
		} else {
			writeToLogAndSetText("Missing Resources Building blocked"); return;
		}
	}
	private void writeToLogAndSetText(String textToSet) {
		logTextPane.writeToLog(textToSet);		
		infoTextPane.setText(textToSet);
	}
	private void writeToLogAndSetTextBought(Item item, Building building) {
		logTextPane.writeToLog("bought: " + item);
		infoTextPane.setText(building+"\n"+resources);
	}
	private void subtracktResources(Item item) {
		SingleResourceTypeWithAmount[] cost = item.getCost();
		for(int subtrackt = 0; subtrackt < resources.getResources().length; subtrackt++) {
			resources.getResources()[subtrackt].removeResourceAmount(cost[subtrackt].getResourceAmount());
		}
	}
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