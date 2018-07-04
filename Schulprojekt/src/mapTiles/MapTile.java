/**
 * @Classname MapTile
 * 
 * @version Alpha
 * 
 * @author Luca Kleck, Timo Bauermeister
 * 
 */
package mapTiles;
public abstract class MapTile {
	/*
	 * An array of this object is a map. Behavior and commands are changed by type of the MapTile
	 * type 0 = Plains
	 * type 1 = Forest
	 * type 2 = Jungle
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
	private int type;
	private boolean traversable;
	private String name;
	private int xPos;
	private int yPos;
	private int[] towerTypes;
	private int[] resourceType;
	private int[] resourceEfficiency;
	// Constructor
	public MapTile(int type, int xPos, int yPos, int[] towerTypes, boolean traversable, int[] resourceType, int[] resourceEfficiency) {
		this.type = type;
		this.xPos = xPos;
		this.yPos = yPos;
		this.towerTypes = towerTypes;
		this.traversable = traversable;
		this.resourceType = resourceType;
		this.resourceEfficiency = resourceEfficiency;
		switch(type) {
			case 0:	this.name = "Plain";
					break;
			case 1: this.name = "Forest";
					break;
			case 2: this.name = "Light Forest";
					break;
			case 3: this.name = "Jungle";
					break;
			/*
			 * 
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

