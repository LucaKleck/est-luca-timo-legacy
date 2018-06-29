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
	private static final int MOUNTAINRANGERADIUSBASE = 3;
	private static final int MAPTILERADIUSBASE = 4;
	private static final int TYPEAMOUNT = 3;
	private static final int MAXRIVERS = 5;
	private static final int MINRIVERS = 3; // Should be a % of map.length 
	private static final int MINRIVERLENGTHPERCENT = 20;
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
	private static void createGreatPlain(MapTile[][] map) {
		for(int y=0; y < map[0].length; y++) {
			for(int x=0; x < map.length; x++) {
					map[x][y] = createCustomMapTile(0,x,y);
			}
		}
	}
	private static void buildMap(MapTile[][] map) {
		for( int type = 0; type <= TYPEAMOUNT; type++) {
			placeX(map, type);
		}
		placeX(map, 20);
	}
	private static void placeX(MapTile[][] map, int type) {
		switch(type) {
			case 0: createGreatPlain(map);
					break;
			case 1: placeSquareRadialType(map, type, 10, 10);
					break;
			case 2: placeSquareRadialType(map, type, 11, 22);
					break;
			case 3: placeSquareRadialType(map,type,1,50);
					break;
			case 20:placeRiverType(map,20,3);
					break;
			default:break;
		}
	}	
	// Placement method with two types of chances
	private static void placeSquareRadialType(MapTile[][] map, int type, int spawnChance, int tileChance) {
		Random random = new Random();
		// first for is going over the map to scan everything, then, if it's in bounds, give it a random chance to create a forest, place forests sporadically.
		for(int y=0; y < map[0].length; y++) {
			for(int x=0; x < map.length; x++) {
				int radius = MAPTILERADIUSBASE + random.nextInt(3);
				if((x-radius) >= 0 && (y-radius) >= 0 && (x-radius) < map.length && (y-radius) < map[0].length && (random.nextInt(100)+1) <= spawnChance ) { 
					for(int yRadius = 0; yRadius <= radius; yRadius++) {
						for( int xRadius = 0; xRadius <= radius; xRadius++ ) {
							if((random.nextInt(100)+1) <= tileChance) {
								map[xRadius+x-radius][yRadius+y-radius] = createCustomMapTile(type,x,y);
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
		int minRiverLength = MINRIVERLENGTHPERCENT*(map.length/100);
		for(int yFirstRow = 0; yFirstRow < map[0].length && rivers < MAXRIVERS; yFirstRow++) {
			if(rivers < MAXRIVERS) {
				if(rivers < MINRIVERS && yFirstRow == (map[0].length-2)) {
					yFirstRow=0;
					//System.err.println("firstRowReset");
				}
				random = new Random();
				if(random.nextInt(100) <= spawnChance) {
					int y = yFirstRow;
					random = new Random();
					// 3 types of rivers (central, up, down)
					switch ((random.nextInt(3)+1)) {
						case 1: rivers++; //System.err.println("---------"+"\n"+"riverType: "+1+"\n"+"rivers: "+rivers );
							rivers = riverWithChance(map, y, minRiverLength, rivers, 25, 25, type);
							break;
						case 2: rivers++; //System.err.println("---------"+"\n"+"riverType: "+2+"\n"+"rivers: "+rivers );
							rivers = riverWithChance(map, y, minRiverLength, rivers, 50, 25, type);
							break;
						case 3: rivers++; //System.err.println("---------"+"\n"+"riverType: "+3+"\n"+"rivers: "+rivers );
							rivers = riverWithChance(map, y, minRiverLength, rivers, 25, 50, type);
							break;
					}
				}
			}
			
		}
	}
	private static int riverWithChance(MapTile[][] map, int y, int minRiverLength, int rivers, int upChance, int downChance, int type) {
		upChance = upChance+downChance;
		for( int x = 0; x < map.length; x++) {
			map[x][y] = createCustomMapTile(type,x,y);
			Random random = new Random();
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
	@SuppressWarnings("unused")
	private static int mountainWithChance(MapTile[][] map, int x, int minMountainLength, int mountainRanges, int upChance, int downChance, int type) {
		upChance = upChance+downChance;
		Random random = new Random();
		for( int y = 0; y < map.length; y++) {
			int radius = (random.nextInt(2)+MOUNTAINRANGERADIUSBASE);
			for(int yRadius = 0; yRadius <= radius; yRadius++) {
				for( int xRadius = 0; xRadius <= radius; xRadius++ ) {
					random = new Random();
					if((random.nextInt(100)+1) <= 100) {
						map[xRadius+x-radius][yRadius+y-radius] = createCustomMapTile(type,x,y);
					}
				}
			} 
			map[x][y] = createCustomMapTile(type,x,y);
			random = new Random();
			int upDownOrNothing = (random.nextInt(100)+1);
			//System.err.println("upDownOrNothing: " + upDownOrNothing +"\n"+"upChance: "+upChance+"\n"+"downChance"+downChance);
			if(upChance > 100 || downChance > 100) return mountainRanges;
			if(upDownOrNothing <= downChance) {
				if(x-1 >= 0)	x--;
				else { if(y <= minMountainLength) {mountainRanges-=1;} y=map.length; }
			}
			else {
				if(upDownOrNothing <= upChance) {
					if(x+1 < map[0].length) x++;
					else { if(y <= minMountainLength) {mountainRanges-=1;} y=map.length; }
				}
			}
		}
		return mountainRanges;
	}
	private static MapTile createCustomMapTile (int type,int x, int y) {
		switch(type) {
			case 0: return new MapTilePlain(x,y);
			case 1: return new MapTileForest(x,y);
			case 2: return new MapTileLightForest(x,y);
			case 3: return new MapTileJungle(x,y);
			case 20:return new MapTileRiver(x,y);
			default:return new MapTile(0,x,y);
		}
	}
// createMap class end
}
