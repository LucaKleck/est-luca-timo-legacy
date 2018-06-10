/**
 * @Classname map
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
	
	// Constructors
	public ObjectMap() {
		copyMap(createMap.createDefault(map));
	}
	public ObjectMap(MapTile[][] loadMap) {
		copyMap(loadMap);
	}
	
	// Methods
	// public
	public MapTile[][] getMap() {
		return map;
	}
	public void printMap() {
		for(int y=0; y < map[0].length; y++) {
			for(int x=0; x < map.length; x++) {
				System.out.print(map[x][y].getType());
			}
			System.out.println("");
		}
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
