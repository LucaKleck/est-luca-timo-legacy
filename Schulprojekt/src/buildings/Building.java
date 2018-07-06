package buildings;

public abstract class Building {
	private static int amountOfBuildings = 0;
	private int buildingID;
	public Building() {
		buildingID = amountOfBuildings;
		amountOfBuildings++;
	}
	public int getAmountOfBuildings() {
		return amountOfBuildings;
	}
	public int getBuildingID() { 
		return buildingID;
	}
}
