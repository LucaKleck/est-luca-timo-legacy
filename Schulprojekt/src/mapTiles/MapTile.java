/**
 * @Classname MapTile
 * 
 * @version Alpha
 * 
 * @author Luca Kleck, Timo Bauermeister
 * 
 */
package mapTiles;
public class MapTile {
	/*
	 * An array of this object is a map. Behavior and commands are changed by type of the MapTile
	 * type 0 = Plains
	 * type 1 = xx
	 * type 2 = xx
	 * type x = xx
	 * ---
	 * towerTypes are all towers that can be placed on top of the tile
	 * Types are as follows: 
	 * type 0: ground
	 * type 1: water
	 * type 3: xx
	 */
	private int type;
	private boolean traversable;
	private String name;
	private int xPos;
	private int yPos;
	private int[] towerTypes;
	// Constructor
	public MapTile(int type, int xPos, int yPos, int[] towerTypes, boolean traversable) {
		this.type = type;
		this.xPos = xPos;
		this.yPos = yPos;
		this.towerTypes = towerTypes;
		this.traversable = traversable;
		switch(type) {
			case 0:	this.name = "Plain"; //System.err.println("This is Plain");
					break;
			case 1: this.name = "Forest"; //System.err.println("This is Forest");
					break;
			case 2: this.name = "Jungle";
					break;
			/*
			 * Insert all types here.
			 */
			case 20: this.name = "River";
			case 69: this.name = "Debug";
			default: this.name = "MissingType";
		}
	}
	// getter
	public int getType() {
		return type;
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
	public String getName() {
		return name;
	}
	public int[] getTowerTypes() {
		return towerTypes;
	}
	// setter
	protected void setTraversable(boolean traversable) {
		this.traversable = traversable;
	}
	// compare
	
}

