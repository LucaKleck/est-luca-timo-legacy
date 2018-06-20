/**
 * @Classname createMap
 * 
 * @version Alpha
 * 
 * @author Luca Kleck, Timo Bauermeister
 * 
 */
import java.util.Random;

import mapTiles.*;
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
	private static final int maxRivers = 3;
	private static final int minRivers = 3;
	private static final int minRiverLengthPercent = 20;
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
		placeX(map, 20);
	}
	private static void placeX(MapTile[][] map, int type) {
		switch(type) {
			case 0: placeEverywhere(map,type);
					break;
			case 1: placeRadialType(map, type, 10, 30);
					break;
			case 2: placeRadialType(map, type, 20, 5);
					break;
			case 20: placeRiverType(map,20,3);
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
								map[xRadius+x-radius][yRadius+y-radius] = createCustomMapTile(type, x, y);
							}
						}
					}	
				}
			}
		}
	}
	// Placement of a river (could become a lava type challenge)
	private static void placeRiverType(MapTile[][] map, int type, int spawnChance) {
		Random random = new Random();
		int rivers = 0;
		int minRiverLength = minRiverLengthPercent*(map.length/100);
		for(int yFirstRow = 0; yFirstRow < map[0].length && rivers < maxRivers; yFirstRow++) {
			if(rivers < maxRivers) {
				if(rivers < minRivers && yFirstRow == (map[0].length-2)) {
					yFirstRow=0;
					System.err.println("firstRowReset");
				}
				if(random.nextInt(100) <= spawnChance) {
					int y = yFirstRow;
					int riverType = (random.nextInt(3)+1);
					// 3 types of rivers (central, up, down)
					switch ((random.nextInt(3)+1)) {
						case 1: rivers++; System.err.println("---------"+"\n"+"riverType: "+riverType+"\n"+"rivers: "+rivers );
							rivers = riverWithChance(map, y, minRiverLength, rivers, 25, 25, random);
							break;
						case 2: rivers++; System.err.println("---------"+"\n"+"riverType: "+riverType+"\n"+"rivers: "+rivers );
							rivers = riverWithChance(map, y, minRiverLength, rivers, 50, 25, random);
							break;
						case 3: rivers++; System.err.println("---------"+"\n"+"riverType: "+riverType+"\n"+"rivers: "+rivers );
							rivers = riverWithChance(map, y, minRiverLength, rivers, 25, 50, random);
							break;
					}
				}
			}
			
		}
	}
	private static int riverWithChance(MapTile[][] map, int y, int minRiverLength, int rivers, int upChance, int downChance, Random random) {
		upChance = upChance+downChance;
		for( int x = 0; x < map.length; x++) {
			map[x][y] = new MapTile(20,x,y);
			int upDownOrNothing = (random.nextInt(100)+1);
			//System.err.println("upDownOrNothing: " + upDownOrNothing +"\n"+"upChance: "+upChance+"\n"+"downChance"+downChance);
			if(upChance > 100 || downChance > 100) return rivers;
			if(upDownOrNothing <= downChance) {
				if(y-1 >= 0)	y--;
				else { if(x <= minRiverLength) {rivers-=1;} x=map.length; }
			}
			else {
				if(upDownOrNothing <= upChance) {
					if(y+1 < map[0].length) y++;
					else { if(x <= minRiverLength) {rivers-=1;} x=map.length; }
				}
			}
		}
		return rivers;
	}
	private static MapTile createCustomMapTile (int type,int x, int y) {
		switch(type) {
			case 0: return new MapTile(0,x,y);
			case 1: return new MapTileForest(x,y);
			default:return new MapTile(0,x,y);
		}
	}
// createMap class end
}
