package buildings;

import java.awt.Color;

import info.ResourceType;
import mapTiles.MapTile;

public abstract class Building {
	private static int amountOfBuildings = 0;
	private int buildingID;
	private String buildingName;
	private ResourceType[] buildableOn;
	private Color color;
	private MapTile mapTile;
	
	public Building(String buildingName, ResourceType[] buildableOn, MapTile mapTile) {
		this.buildableOn = buildableOn;
		this.buildingName = buildingName;
		this.mapTile = mapTile;
		buildingID = amountOfBuildings;
		switch(buildingName) {
			case "Lumbercamp":
					color = Color.orange.darker();
					break;
			default: color = new Color(200,200,200);
					 break;
		}
		amountOfBuildings++;
	}
	public MapTile getMapTile() {
		return mapTile;
	}
	public Color getColor() {
		return color;
	}
	public int getAmountOfBuildings() {
		return amountOfBuildings;
	}
	public ResourceType[] getBuildableOn() {
		return buildableOn;
	}
	public int getID() { 
		return buildingID;
	}
	public String getName() {
		return buildingName;
	}
	public String toString() {
		String string = "ID: " + buildingID + " Name: " + buildingName;
		return string;
	}
}
