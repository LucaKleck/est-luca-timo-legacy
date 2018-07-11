/**
 * @Classname MapTile
 * 
 * @version Alpha
 * 
 * @author Luca Kleck, Timo Bauermeister
 * 
 */
package mapTiles;

import staticPackage.MapTileType;

public abstract class MapTile{
	/*
	 * An array of this object is a map. Behavior and commands are changed by type of the MapTile
	 * type 0 = Plains
	 * type 1 = Forest
	 * type 2 = LightForest
	 * type 3 = Jungle
	 * type 20 = River
	 * type 30 = Mountain
	 * ---
	 * towerTypes are all towers that can be placed on top of the tile
	 * Types are as follows: 
	 * type 0: ground
	 * type 1: water
	 * type 3: xx
	 * ---
	 * resourceTypes:
	 * type 0: food;
	 * type 1: wood;
	 * type 3: stone;
	 * type 4: Magic Stones
	 */
	private MapTileType type;
	private boolean traversable;
	private int xPos;
	private int yPos;
	private double width;
	private double height;
	private int[] towerTypes;
	private int[] resourceType;
	private int[] resourceEfficiency;

	// Constructor
	public MapTile(MapTileType type, int xPos, int yPos, int[] towerTypes, boolean traversable, int[] resourceType, int[] resourceEfficiency) {
		this.type = type;
		this.xPos = xPos;
		this.yPos = yPos;
		this.towerTypes = towerTypes;
		this.traversable = traversable;
		this.resourceType = resourceType;
		this.resourceEfficiency = resourceEfficiency;
	}
	//Methods
	public String toString() {
		String str = "MapTile \n" + "Type: " + type.getName() + " xPos: " + this.xPos + " yPos: " + this.yPos;
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
	public int[] getTowerTypes() {
		return towerTypes;
	}
	public double getWidth() {
		return this.width;
	}
	public double getHeight() {
		return this.height;
	}
	// setter
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

