/**
 * @Classname createMap
 * 
 * @version Alpha
 * 
 * @author Luca Kleck, Timo Bauermeister
 * 
 */
public class createMap {
/*
 * 1. Size
 * 2. Run method forest and so on...
 * 3. Run river
 * 4. Run mountain
 * 5. Run points of interest
 * 6. Run cities
 * 7. Run story points
 * 8. Call class map, copy finished MapTile[][] map to object map.
 */
	public static MapTile[][] createCustom( int x, int y) {
		MapTile[][] map = new MapTile[x][y];
		placeEverywhere(map,1);
		return map;

	}
	public static MapTile[][] createDefault(MapTile[][] map) {
		map = new MapTile[20][20];
		placeEverywhere(map,0);
		return map;
	}
	private static void placeEverywhere(MapTile[][] map, int type) {
		
		switch(type) {
				
			case 0: placePlains(map);		
					break;
			case 1: placeForest(map);
					break;
		}
			
	}
	private static void placeForest(MapTile[][] map) {
		for(int y=0; y < map.length; y++) {
			for(int x=0; x < map[0].length; x++) {
				map[x][y] = new MapTileForest();
			}
		}
	}
	private static void placePlains(MapTile[][] map) {
		for(int y=0; y < map.length; y++) {
			for(int x=0; x < map[0].length; x++) {
				map[x][y] = new MapTilePlains();
			}
		}
	}
	
	
}
