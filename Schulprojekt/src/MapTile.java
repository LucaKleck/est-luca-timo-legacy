
public class MapTile {
	private int type;
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
	
}

