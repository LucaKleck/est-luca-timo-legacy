/**
 * @Classname MapTile
 * 
 * @version Alpha
 * 
 * @author Luca Kleck, Timo Bauermeister
 * 
 */
package mapTiles;

import buildings.Building;
import staticPackage.MapTileType;

public abstract class MapTile{
	/*
	 * An array of this object is a map. Behavior and commands are changed by type of the MapTile
	 */
	private MapTileType type;
	private boolean traversable;
	private Building building;
	private int xPos;
	private int yPos;
	private double width;
	private double height;
	private int[] resourceType;
	private int[] resourceEfficiency;
	// Constructor
	public MapTile(MapTileType type, int xPos, int yPos, boolean traversable, int[] resourceType, int[] resourceEfficiency) {
		this.type = type;
		this.xPos = xPos;
		this.yPos = yPos;
		this.traversable = traversable;
		this.resourceType = resourceType;
		this.resourceEfficiency = resourceEfficiency;
	}
	//Methods
	public String toString() {
		String str = "MapTile \n" + "Type: " + type.getName() + " xPos: " + this.xPos + " yPos: " + this.yPos + "building: " + building;
		return str;
	}
	// getter
	public MapTileType getMapTileType() {
		return type;
	}
	public int[] getResourceType() {
		return resourceType;
	}
	public int[] getResourceEfficiency() {
		return resourceEfficiency;
	}
	public int getXPos() {
		return xPos;
	}
	public int getYPos() {
		return yPos;
	}
	public boolean getTraversable() {
		return traversable;
	}
	public double getWidth() {
		return this.width;
	}
	public double getHeight() {
		return this.height;
	}
	public Building getBuilding() {
		return building;
	}
	// setter
	public void setBuilding(Building building) {
		this.building = building;
	}
	protected void setTraversable(boolean traversable) {
		this.traversable = traversable;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public void setHeight(double  height) {
		this.height = height;
	}
	// compare
	
}

