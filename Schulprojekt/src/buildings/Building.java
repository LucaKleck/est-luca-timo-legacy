package buildings;

import java.awt.Color;

public abstract class Building {
	private static int amountOfBuildings = 0;
	private int buildingID;
	private String buildingName;
	private int[] buildableOn;
	private Color color;
	public Building(String buildingName, int[] buildableOn) {
		this.buildableOn = buildableOn;
		this.buildingName = buildingName;
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
	public Color getColor() {
		return color;
	}
	public int getAmountOfBuildings() {
		return amountOfBuildings;
	}
	public int[] getBuildableOn() {
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
