/**
 * @Classname createMap
 * 
 * @version Alpha
 * 
 * @author Luca Kleck, Timo Bauermeister
 * 
 */
import java.util.Random;
public class createMap {
/*
 * 1. Size
 * 2. Create MapTiles
 * 3. Run river
 * 4. Run mountain
 * 5. Run points of interest
 * 6. Run cities
 * 7. Run story points
 * 8. Call class map, copy finished MapTile[][] map to object map.
 */
	private static final int radiusBase = 4;
	public static MapTile[][] createCustom( int x, int y) {
		MapTile[][] map = new MapTile[x][y];
		placeEverywhere(map,1);
		return map;

	}
	public static MapTile[][] createDefault(MapTile[][] map) {
		map = new MapTile[20][20];
		placeEverywhere(map,0); // Type for debug, remove in final build
		placeForest(map);
		return map;
	}
	private static void placeEverywhere(MapTile[][] map, int type) {
		for(int y=0; y < map.length; y++) {
			for(int x=0; x < map[0].length; x++) {
					map[x][y] = new MapTile(type);
			}
		}
	}
	private static void placeForest(MapTile[][] map) {
		Random random = new Random();
		// add radius
		int radius = radiusBase + random.nextInt(3);		
		for(int y=0; y < map.length; y++) {
			for(int x=0; x < map[0].length; x++) {
				if((x - radius) >= 0 && (y-radius) >= 0 || (x+radius) < map.length && (y+radius) < map[0].length) ;
				/*if(random.nextInt(100) > 30) {
					map[x][y] = new MapTile(1);
				}*/
			}
		}
	}
			
	
	
	
}
