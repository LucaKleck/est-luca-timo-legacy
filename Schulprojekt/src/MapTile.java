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
	 * An array of this object is a map, behavior and commands are changed by type of the MapTile
	 * type 0 = Plains
	 * type 1 = xx
	 * type 2 = xx
	 * type x = xx
	 */
	private int type;
	private boolean searched = false;
	private String name;
	
	public MapTile() {
		this.type = 0;
		this.name = "Plains";
	}
	public MapTile(int type, String name) {
		this.type = type;
		this.name = name;
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
	// setter
	
	// compare
	
}

