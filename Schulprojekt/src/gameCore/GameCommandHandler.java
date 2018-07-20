package gameCore;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;
import javax.swing.JTextPane;

import buildings.Building;
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
	private MapTile[][] map;
	private JTextPane infoTextPane; 
	private LogTextPane logTextPane;
	private JTabbedPane tabbedPane;
	private DrawMapTile[][] drawMapTileArray;
	private DrawMap drawMap;
	
	public GameCommandHandler(MainJFrame mainJFrame,ObjectMap objectMap) {
		this.mainJFrame = mainJFrame;
		this.objectMap = objectMap;
		this.map = objectMap.getMap();
		this.infoTextPane = mainJFrame.getInfoTextPane();
		this.logTextPane = mainJFrame.getLogTextPane();
		this.tabbedPane = mainJFrame.getTabbedPane();
		this.drawMapTileArray = mainJFrame.getDrawMapTileArray();
		this.drawMap = mainJFrame.getDrawMap();
	}

	// Methods
	public String sendCommand(String command, Object source) {
		String result = "";
		System.out.println(source);
		System.out.println(source.getClass() + " vs "+ DrawMapTile.class);
		System.out.println(source.getClass().equals(DrawMapTile.class));
		System.out.println(source.equals(DrawMapTile.class));
		
		if(source.getClass().equals(DrawMapTile.class)) {
			result = drawMapTileCommands(command,(DrawMapTile) source);
		}
		return result;
	}
	public String drawMapTileCommands(String command, DrawMapTile source) {
		String result = "noIssue";
		MapTile mapTile = source.getMapTile();
		if(!command.isEmpty() && command!="select") {
			boolean isBuilding = false;
    		Item item = new Item(stripBuyString(command)); // This strips the string off of building information and buy
			if(command.contains(",Building")) {
				isBuilding = true;
			}
			// buys item(containing price) on MapTile mapTile, costs the global Resources
			buyItem(item, isBuilding, mapTile, mainJFrame.getResources());
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
			try {
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
	private String stripBuyString(String fullString) {
		String result = "";
		for(int search = 3; search < fullString.length(); search++) {
			if(fullString.charAt(search) == ',') {
				return result;
			}
			result += fullString.charAt(search);
		}
		return result;
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
	public void buyItem(Item item, boolean isBuilding, MapTile mapTile, ResourcesController resources) {
		if(isBuilding) {
			switch(item.getItemName()) {
				case "Town Hall":	buyTownHall(mainJFrame, mapTile, resources, item);
									break;
				case "Lumbercamp":	buyLumbercamp(mainJFrame, mapTile, resources, item);
									break;
				default: writeToLogAndSetText("Missing Resources/Building blocked + DEFAULTCASE!!! (not good, something bad happened)"); return;
			}
		}
	}
	private void buyLumbercamp(MainJFrame mainJFrame, MapTile mapTile, ResourcesController resources, Item item) {
		if(mapTile.getBuilding() == null) {
			boolean hasResources = item.hasResources(resources, (MapTileWithResources) mapTile);
			if(hasResources) {
				Building building = new Lumbercamp(mapTile);
				subtracktResources(item, resources);
				mapTile.setBuilding(building);
				writeToLogAndSetTextBought(mainJFrame, item, building, resources);
			} else {
				writeToLogAndSetText("Missing Resources/Invalid Field"); return;
			}
		} else {
			writeToLogAndSetText("Missing Resources/Building blocked");	return;
		}
	}
	private void buyTownHall(MainJFrame mainJFrame, MapTile mapTile, ResourcesController resources, Item item) {
		if(mapTile.getBuilding() == null && mapTile.getMapTileType().getType() != 20) {
			Building building = new TownHall(mainJFrame, mapTile);
			subtracktResources(item, resources);
			mapTile.setBuilding(building);
			writeToLogAndSetTextBought(mainJFrame, item, mapTile.getBuilding(), resources);
		} else {
			writeToLogAndSetText("Missing Resources Building blocked"); return;
		}
	}
	private void writeToLogAndSetText(String textToSet) {
		logTextPane.writeToLog(textToSet);		
		infoTextPane.setText(textToSet);
	}
	private void writeToLogAndSetTextBought(MainJFrame mainJFrame, Item item, Building building, ResourcesController resources) {
		logTextPane.writeToLog("bought: " + item);
		infoTextPane.setText(building+"\n"+resources);
	}
	private void subtracktResources(Item item, ResourcesController resources) {
		SingleResourceTypeWithAmount[] cost = item.getCost();
		for(int subtrackt = 0; subtrackt < resources.getResources().length; subtrackt++) {
			resources.getResources()[subtrackt].removeResourceAmount(cost[subtrackt].getResourceAmount());
		}
	}
}
