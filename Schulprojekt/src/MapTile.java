/**
 * @Classname MapTile
 * 
 * @version Alpha
 * 
 * @author Luca Kleck, Timo Bauermeister
 * 
 */
public class MapTile {
	/*
	 * An array of this object is a map. Behavior and commands are changed by type of the MapTile
	 * type 0 = Plains
	 * type 1 = xx
	 * type 2 = xx
	 * type x = xx
	 */
	private int type;
	private boolean searched = false;
	private String name;
	private int xPos;
	private int yPos;
	
	public MapTile() {
		this.type = 0;
		this.name = "Plains";
	}
	public MapTile(int type, String name, int xPos, int yPos) {
		this.type = type;
		this.name = name;
		this.xPos = xPos;
		this.yPos = yPos;
	}
	public MapTile(int type, int x, int y) {
		this.type = type;
		this.xPos = x;
		this.yPos = y;
		switch(type) {
		case 0:	this.name = "Plains";
				break;
		case 1: this.name = "Forest";
				break;
		case 2: this.name = "xx";
				break;
		/*
		 * Insert all types here.
		 */
		case 20: this.name = "River";
		case 69: this.name = "Debug";
		default:this.name = "MissingType";
		}
	}
	// getter
	public int getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public boolean getSearched() {
		return searched;
	}
	public int getXPos() {
		return xPos;
	}
	public int getYPos() {
		return yPos;
	}
	// setter
	
	// compare
	
}

