package buildings;

public abstract class Building {
	private static int amountOfBuildings = 0;
	private int buildingID;
	private String buildingName;
	private int[] buildableOn;
	public Building(String buildingName, int[] buildableOn) {
		this.buildableOn = buildableOn;
		this.buildingName = buildingName;
		buildingID = amountOfBuildings;
		amountOfBuildings++;
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
