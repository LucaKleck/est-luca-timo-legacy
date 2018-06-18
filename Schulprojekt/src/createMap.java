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
	private static final int typeAmount = 2;
	private static final int maxRivers = 4;
	public static MapTile[][] createCustom( int x, int y) {
		MapTile[][] map = new MapTile[x][y];
		buildMap(map);
		return map;

	}
	public static MapTile[][] createDefault(MapTile[][] map) {
		map = new MapTile[100][100];
		buildMap(map);
		return map;
	}
	private static void placeEverywhere(MapTile[][] map, int type) { // Remove type in final build.
		for(int y=0; y < map[0].length; y++) {
			for(int x=0; x < map.length; x++) {
					map[x][y] = new MapTile(type,x,y);
			}
		}
	}
	private static void buildMap(MapTile[][] map) {
		for( int type = 0; type <= typeAmount; type++) {
			placeX(map, type);
		}
		placeRiverType(map,20,3,0);
	}
	private static void placeX(MapTile[][] map, int type) {
		switch(type) {
		case 0: placeEverywhere(map,type);
				break;
		case 1: placeRadialType(map, type, 10, 30);
				break;
		case 2: placeRadialType(map, type, 20, 5);
				break;
		default:placeRadialType(map, 0, 0, 0);
		}
	}	
	// Placement method with two types of chances
	private static void placeRadialType(MapTile[][] map, int type, int spawnChance, int tileChance) {
		Random random = new Random();
		// first for is going over the map to scan everything, then, if it's in bounds, give it a random chance to create a forest, place forests sporadically.
		for(int y=0; y < map[0].length; y++) {
			for(int x=0; x < map.length; x++) {
				int radius = radiusBase + random.nextInt(3);
				if((x-radius) >= 0 && (y-radius) >= 0 && (x-radius) < map.length && (y-radius) < map[0].length && (random.nextInt(100)+1) <= spawnChance ) { 
					for(int yRadius = 0; yRadius <= radius; yRadius++) {
						for( int xRadius = 0; xRadius <= radius; xRadius++ ) {
							if((random.nextInt(100)+1) <= tileChance) {
								map[xRadius+x-radius][yRadius+y-radius] = new MapTile(type, x, y);
							}
						}
					}	
				}
			}
		}
	}
	// Placement of a river (could become a lava type challenge)
	private static void placeRiverType(MapTile[][] map, int type, int spawnChance, int rivers) {
		Random random = new Random();
		for( int yFirstRow = 0; yFirstRow < map[0].length; yFirstRow++) {
			while(rivers < maxRivers) {
				System.out.println("rivers: "+rivers);
				if(random.nextInt(100) <= spawnChance) {
					int y = yFirstRow;
					rivers++;
					// 3 types of rivers (central, up, down)
					switch (random.nextInt(3)+1) {
					case 1: 
						for( int x = 0; x < map.length; x++) {
							map[x][y] = new MapTile(20,x,y);
							switch (random.nextInt(4)+1) {
							case 1: if(y+1 < map[0].length) y++;
									break;
							case 2: if(y-1 > 0) y--;
									break;
							case 3:	case 4: break;
							}
						}
					case 2: 
						for( int x = 0; x < map.length; x++) {
							map[x][y] = new MapTile(20,x,y);
							switch (random.nextInt(7)+1) {
							case 1:	case 2:	case 3: if(y+1 < map[0].length) {
									y++;
							}
							else	return;
									break;
							case 4: if(y-1 > 0) y--;
									break;
							case 5:	case 6: case 7:
									break;
							}
						}
					case 3: 
						for( int x = 0; x < map.length; x++) {
							map[x][y] = new MapTile(20,x,y);
							switch (random.nextInt(7)+1) {
							case 1:	case 2:	case 3: if(y-1 > 0) {
									y--;
							}
							else {
								return;
							}
									break;
							case 4: if(y+1 < map[0].length) y++;
									break;
							case 5:	case 6: case 7: 
									break;
							}
						}
					}
				}
			}
			if( rivers == 0) {
				placeRiverType(map,20,3,rivers);
			}
		}
	}
	
}
