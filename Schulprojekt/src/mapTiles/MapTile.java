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
import info.ResourceType;
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
	private ResourceType[] resourceType;
	private int[] resourceEfficiency;
	// Constructor
	public MapTile(MapTileType type, int xPos, int yPos, boolean traversable, ResourceType[] resourceType, int[] resourceEfficiency) {
		this.type = type;
		this.xPos = xPos;
		this.yPos = yPos;
		this.traversable = traversable;
		this.resourceType = resourceType;
		this.resourceEfficiency = resourceEfficiency;
	}
	//Methods
	public String toString() {
		String str = "";
		try {
			str = "MapTile" + type.getName() + " xPos: " + this.xPos + " yPos: " + this.yPos + "building: " + building+"\nresourceType(s): "+resourceType[0]+": "+resourceEfficiency[0]+"%, "+resourceType[1]+": "+resourceEfficiency[1]+"%";
		} catch(IndexOutOfBoundsException e) {
			str = "MapTile" + type.getName() + " xPos: " + this.xPos + " yPos: " + this.yPos + "building: " + building+"\nresourceType(s): "+resourceType[0]+": "+resourceEfficiency[0]+"%";
		}
		return str;
	}
	// getter
	public MapTileType getMapTileType() {
		return type;
	}
	public ResourceType[] getResourceType() {
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

