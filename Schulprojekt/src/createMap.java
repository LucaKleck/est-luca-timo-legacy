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
    private static final int MAPTILERADIUSBASE = 4;
	private static final int TYPEAMOUNT = 3;
	private static final int MAXRIVERS = 2;
	private static final int MINRIVERS = 2; // Should be a % of map.length 
	private static final int MINRIVERLENGTHPERCENT = 20;
	public static MapTile[][] createCustom( int x, int y) {
		MapTile[][] map = new MapTile[x][y];
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
			if(y+1 < map[0].length) map[x][y+1] = createCustomMapTile(type,x,y);
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
	private static MapTile createCustomMapTile (int type, int x, int y) {
		Random random = new Random();
		int[] resourceType;
		int[] resourceEfficiency;
		switch(type) {
			case 0: resourceType = new int[] {0,random.nextInt(5)};
					resourceEfficiency = new int[] {100, random.nextInt(100+1)};
					if(resourceType[1] == resourceType[0]) {
						resourceType = new int[] {resourceType[0]};
						resourceEfficiency = new int[] {resourceEfficiency[0]};
					}
					return new MapTilePlain(x,y,resourceType,resourceEfficiency);
			case 1: resourceType = new int[] {1,random.nextInt(5)};
					resourceEfficiency = new int[] {100, random.nextInt(100+1)};
					if(resourceType[1] == resourceType[0]) {
						resourceType = new int[] {resourceType[0]};
						resourceEfficiency = new int[] {resourceEfficiency[0]};
					}
					return new MapTileForest(x,y,resourceType,resourceEfficiency);
			case 2: resourceType = new int[] {1,random.nextInt(5)};
					resourceEfficiency = new int[] {70, random.nextInt(100+1)};
					if(resourceType[1] == resourceType[0]) {
						resourceType = new int[] {resourceType[0]};
						resourceEfficiency = new int[] {resourceEfficiency[0]};
					}
					return new MapTileLightForest(x,y,resourceType,resourceEfficiency);
			case 3: resourceType = new int[] {0,1};
					resourceEfficiency = new int[] {100, 100};
					return new MapTileJungle(x,y,resourceType,resourceEfficiency);
			case 20:resourceType = new int[] {4,random.nextInt(5)};
					resourceEfficiency = new int[] {60, random.nextInt(100+1)};
					if(resourceType[1] == resourceType[0]) {
						resourceType = new int[] {resourceType[0]};
						resourceEfficiency = new int[] {resourceEfficiency[0]};
					}
					return new MapTileRiver(x,y,resourceType,resourceEfficiency);
			default:resourceType = new int[] {1,random.nextInt(5)};
					resourceEfficiency = new int[] {100, random.nextInt(100+1)};
					if(resourceType[1] == resourceType[0]) {
						resourceType = new int[] {resourceType[0]};
						resourceEfficiency = new int[] {resourceEfficiency[0]};
					}
					resourceType = checkResourceTypeLogic(resourceType,resourceEfficiency);
					resourceEfficiency = checkResourceEfficiencyLogic(resourceType, resourceEfficiency);
					return new MapTilePlain(x,y,resourceType,resourceEfficiency);
		}
	}
	private static int[] checkResourceTypeLogic(int[] resourceType, int[] resourceEfficiency) {
		
		return resourceType;
	}
	private static int[] checkResourceEfficiencyLogic(int[] resourceType, int[] resourceEfficiency) {
		
		return resourceEfficiency;
	}
//  Fuck u Was cooles!
//	DOIT create city
//	DOIT create pathway IDEA create portal for enemy spawn IDEA create tower that defend the portal IDEA create buff boy to defend portal
// 	createMap class end
}
