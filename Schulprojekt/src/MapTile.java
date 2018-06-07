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
	 * An array of this object is a map, subclasses specify type, behaviour and commands that can be used
	 */
	private int type;
	private boolean searched = false;
	private String name;
	
	public MapTile() {
		this.type = 0;
		this.name = "Grassland";
	}
	public MapTile(int type, String name) {
		this.type = type;
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public boolean getSearched() {
		return searched;
	}
}

