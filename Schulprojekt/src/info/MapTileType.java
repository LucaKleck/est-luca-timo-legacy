package info;

import java.awt.Color;

public enum MapTileType {
	Plain(0, "Plain", new Color(56, 216, 59)),
	Forest(1, "Forest", new Color(0,100,0)),
	LightForest(2, "LightForest", new Color(10,130,10)),
	Jungle(3, "Jungle", new Color(66,147,33)),
	River(20, "River", new Color(0,30,255)),
	Mountain(30, "Mountain", new Color(160,150,60)),
	DefaultRoad(40, "Road", new Color(30,30,30)),
	DirtRoad(41,"Dirt Road",new Color(200,150,40)),
	Bridge(42,"Bridge",new Color(0,30,30));
	
	private final int type;
	private final String name;
	private final Color color;
	
	MapTileType(int type, String name, Color color) {
		this.type = type;
		this.name= name;
		this.color = color;
	}
	public int getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public Color getColor() {
		return color;
	}
}
