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
		doStuff(map,0);
		return map;

	}
	public static MapTile[][] createDefault(MapTile[][] map) {
		map = new MapTile[20][20];
		doStuff(map,0);
		return map;
	}
	private static void placeEverywhere(MapTile[][] map, int type) { // Remove type in final build.
		for(int y=0; y < map.length; y++) {
			for(int x=0; x < map[0].length; x++) {
					map[x][y] = new MapTile(type);
			}
		}
	}
	private static void doStuff(MapTile[][] map, int type) {
		placeEverywhere(map,type);
		placeForests(map);
	}
	private static void placeForests(MapTile[][] map) {
		Random random = new Random();
			
		// first for is going over the map to scan everything, then, if it's in bounds, give it a random chance to create a forest, place forests sporadically.
		for(int y=0; y < map.length; y++) {
			for(int x=0; x < map[0].length; x++) {
				int radius = radiusBase + random.nextInt(3);
				if((x-radius) >= 0 && (y-radius) >= 0 && (x-radius) < map.length && (y-radius) < map[0].length && (random.nextInt(100)+1) <= 5 ) { // chance is currently 100, change soon!
					
					
					for(int yRadius = 0; yRadius < radius; yRadius++) {
						for( int xRadius = 0; xRadius < radius; xRadius++ ) {
							if((random.nextInt(100)+1) <= 60) {
								map[xRadius+x-radius][yRadius+y-radius] = new MapTile(1);
							}
						}
						
						
					}	
				}
			}
		}
	}
			
	
	
	
}
