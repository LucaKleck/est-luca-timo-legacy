/**
 * @Classname ObjectMap
 * 
 * @version Alpha
 * 
 * @author Luca Kleck, Timo Bauermeister
 * 
 */
public class ObjectMap {
	/*
	 * Class map is for loading an existing map or creating a new one, then having special functions related to map movement or editing.
	 */
	
	private MapTile[][] map;
	private int width;
	private int height;
	// Constructors
	public ObjectMap() {
		copyMap(createMap.createDefault(map));
		width = 100;
		height = 100;
	}
	public ObjectMap(MapTile[][] loadMap, int width, int height) {
		copyMap(loadMap);
		this.width = width;
		this.height = height;
	}
	// Methods
	// public
	public void printMap() {
		for(int y=0; y < map[0].length; y++) {
			for(int x=0; x < map.length; x++) {
				System.out.print(map[x][y].getType());
			}
			System.out.println("");
		}
	}
	// getter
	public MapTile[][] getMap() {
		return map;
	}
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	// private
	private void copyMap(MapTile[][] tempMap) {
		map = new MapTile[tempMap.length][tempMap[0].length];
		
		for(int y=0; y < map[0].length; y++) {
			for(int x=0; x < map.length; x++) {
				map[x][y] = tempMap[x][y];
			}
		}
	}
	
}
