package gameCore;
/**
 * @Classname createMap
 * 
 * @version Alpha
 * 
 * @author Luca Kleck, Timo Bauermeister
 * 
 */
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import info.ResourceType;
import mapTiles.MapTile;
import mapTiles.MapTileForest;
import mapTiles.MapTileJungle;
import mapTiles.MapTileLightForest;
import mapTiles.MapTileMountain;
import mapTiles.MapTilePlain;
import mapTiles.MapTileRiver;
public class CreateMap {
	private static Logger log = GameCoreController.log;
    private static final int MAPTILERADIUSBASE = 4;
	private static final int TYPEAMOUNT = 3;
	private static final int MAXRIVERS = 2;
	private static final int MINRIVERS = 2; // Could be a % of map.length 
	private static final int MINRIVERLENGTHPERCENT = 20;
	
	public static MapTile[][] createCustom(int x, int y) {
		log.info("Start createCustom map");
		MapTile[][] map = new MapTile[x][y];
		buildMap(map);
		log.info("End createCustom map");
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
		createGreatPlain(map);
		placeX(map, 30);
		for( int type = 0; type <= TYPEAMOUNT; type++) {
			placeX(map, type);
		}
		placeX(map, 20);
	}
	private static void placeX(MapTile[][] map, int type) {
		switch(type) {
			case 0: placeSquareRadialType(map, type, 300, 10);
					break;
			case 1: placeSquareRadialType(map, type, 100, 300);
					break;
			case 2: placeSquareRadialType(map, type, 110, 220);
					break;
			case 3: placeSquareRadialType(map,type,100,100);
					break;
			case 20:placeRiverType(map,20,3);
					break;
			case 30:placeSquareRadialType(map, type, 5, 700);
			default:return;
		}
	}	
	// Placement method with two types of chances
	private static void placeSquareRadialType(MapTile[][] map, int type, int spawnChance, int tileChance) {
		Random random = new Random();
		// first for is going over the map to scan everything, then, if it's in bounds, give it a random chance to create a forest, place forests sporadically.
		for(int y=0; y < map[0].length; y++) {
			for(int x=0; x < map.length; x++) {
				int radius = MAPTILERADIUSBASE + random.nextInt(3);
				if((x-radius) >= 0 && (y-radius) >= 0 && (x-radius) < map.length && (y-radius) < map[0].length && (random.nextInt(1000)+1) <= spawnChance ) { 
					for(int yRadius = 0; yRadius <= radius; yRadius++) {
						for( int xRadius = 0; xRadius <= radius; xRadius++ ) {
							if((random.nextInt(1000)+1) <= tileChance) {
								map[xRadius+x-radius][yRadius+y-radius] = createCustomMapTile(type,map[xRadius+x-radius][yRadius+y-radius].getXPos(),map[xRadius+x-radius][yRadius+y-radius].getYPos());
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
				}
				random = new Random();
				if(random.nextInt(100) <= spawnChance) {
					int y = yFirstRow;
					random = new Random();
					// 3 types of rivers (central, up, down)
					switch ((random.nextInt(3)+1)) {
						case 1: rivers++; log.log(Level.FINE, "Debug: ",("---------"+"\n"+"riverType: "+1+"\n"+"rivers: "+rivers)); 
							rivers = riverWithChance(map, y, minRiverLength, rivers, 25, 25, type);
							break;
						case 2: rivers++; log.log(Level.FINE, "---------"+"\n"+"riverType: "+2+"\n"+"rivers: "+rivers );
							rivers = riverWithChance(map, y, minRiverLength, rivers, 50, 25, type);
							break;
						case 3: rivers++; log.log(Level.FINE, "---------"+"\n"+"riverType: "+3+"\n"+"rivers: "+rivers );
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
			if(y+1 < map[0].length) map[x][y+1] = createCustomMapTile(type,x,y+1);
			Random random = new Random();
			int upDownOrNothing = (random.nextInt(100)+1);
			log.log(Level.FINE, "upDownOrNothing: " + upDownOrNothing +"\n"+"upChance: "+upChance+"\n"+"downChance"+downChance); 
			if(upChance > 100 || downChance > 100) return rivers;
			if(upDownOrNothing <= downChance) {
				if(y-1 >= 0) y--;
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
	private static MapTile createCustomMapTile(int type, int x, int y) {
		switch(type) {
			case 0: return createMapTilePlain(x, y);
			case 1: return createMapTileForest(x,y);
			case 2: return createMapTileLightForest(x,y);
			case 3: return createMapTileJungle(x, y);
			case 20:return createMapTileRiver(x,y);
			case 30:return new MapTileMountain(x,y);
			default:return createMapTilePlain(x, y);
		}
	}
	private static MapTile createMapTilePlain(int x, int y) {
		Random random = new Random();
		ResourceType[] resourceTypeTemp = new ResourceType[] {ResourceType.Food, createResourceTypeFromInt(random.nextInt(5)+1)}; 
		int[] resourceEfficiencyTemp = new int[] {100, random.nextInt(101)};
		ResourceType[] resourceType = checkResourceTypeLogic(resourceTypeTemp,resourceEfficiencyTemp);
		int[] resourceEfficiency = checkResourceEfficiencyLogic(resourceTypeTemp, resourceEfficiencyTemp);
		return new MapTilePlain(x,y,resourceType,resourceEfficiency);
	}
	private static MapTile createMapTileForest(int x, int y) {
		Random random = new Random();
		ResourceType[] resourceTypeTemp = new ResourceType[] {ResourceType.Wood, createResourceTypeFromInt(random.nextInt(5)+1)}; 
		int[] resourceEfficiencyTemp = new int[] {100, random.nextInt(101)};
		ResourceType[] resourceType = checkResourceTypeLogic(resourceTypeTemp,resourceEfficiencyTemp);
		int[] resourceEfficiency = checkResourceEfficiencyLogic(resourceTypeTemp, resourceEfficiencyTemp);
		return new MapTileForest(x,y,resourceType,resourceEfficiency);
	}
	private static MapTile createMapTileLightForest(int x, int y) {
		Random random = new Random();
		ResourceType[] resourceTypeTemp = new ResourceType[] {ResourceType.Wood, createResourceTypeFromInt(random.nextInt(5)+1)}; 
		int[] resourceEfficiencyTemp = new int[] {70, random.nextInt(101)};
		ResourceType[] resourceType = checkResourceTypeLogic(resourceTypeTemp,resourceEfficiencyTemp);
		int[] resourceEfficiency = checkResourceEfficiencyLogic(resourceTypeTemp, resourceEfficiencyTemp);
		return new MapTileLightForest(x,y,resourceType,resourceEfficiency);
	}
	private static MapTile createMapTileJungle(int x, int y) {
		ResourceType[] resourceTypeTemp = new ResourceType[] {ResourceType.Wood, ResourceType.Food}; 
		int[] resourceEfficiencyTemp = new int[] {100, 100};
		ResourceType[] resourceType = checkResourceTypeLogic(resourceTypeTemp,resourceEfficiencyTemp);
		int[] resourceEfficiency = checkResourceEfficiencyLogic(resourceTypeTemp, resourceEfficiencyTemp);
		return new MapTileJungle(x,y,resourceType,resourceEfficiency);
	}
	private static MapTile createMapTileRiver(int x, int y) {
		ResourceType[] resourceTypeTemp = new ResourceType[] {ResourceType.Food, ResourceType.ManaStones}; 
		int[] resourceEfficiencyTemp = new int[] {100,100};
		ResourceType[] resourceType = checkResourceTypeLogic(resourceTypeTemp,resourceEfficiencyTemp);
		int[] resourceEfficiency = checkResourceEfficiencyLogic(resourceTypeTemp, resourceEfficiencyTemp);
		return new MapTileRiver(x,y,resourceType,resourceEfficiency);
	}
	private static ResourceType createResourceTypeFromInt(int i) {
		ResourceType resourceType;
		switch(i) {
			case 0: resourceType = ResourceType.Money;
					break;
			case 1: resourceType = ResourceType.Food;
					break;
			case 2: resourceType = ResourceType.Wood;
					break;
			case 3: resourceType = ResourceType.Stone;
					break;
			case 4: resourceType = ResourceType.Metal;
					break;
			case 5: resourceType = ResourceType.ManaStones;
					break;
			default: resourceType = ResourceType.Food;
		}
		return resourceType;
	}
	private static ResourceType[] checkResourceTypeLogic(ResourceType[] resourceTypeTemp, int[] resourceEfficiencyTemp) {
		if(resourceEfficiencyTemp[1] <= 0) {
			resourceTypeTemp = new ResourceType[] {resourceTypeTemp[0]};
		}
		try {
			if(resourceTypeTemp[1].getType() == resourceTypeTemp[0].getType()) {
				resourceTypeTemp = new ResourceType[] {resourceTypeTemp[0]};
			}
		} catch(IndexOutOfBoundsException e) {
			log.log(Level.FINE, "checkResourceTypeLogic: ", e.getCause());
		}
		return resourceTypeTemp;
	}
	private static int[] checkResourceEfficiencyLogic(ResourceType[] resourceTypeTemp, int[] resourceEfficiencyTemp) {
		if(resourceEfficiencyTemp[1] <= 0){
			resourceEfficiencyTemp = new int[] {resourceEfficiencyTemp[0]};
		}
		try {
			if(resourceTypeTemp[1].getType() == resourceTypeTemp[0].getType()) {
				if(resourceEfficiencyTemp[0] >= resourceEfficiencyTemp[1]) {
					resourceEfficiencyTemp = new int[] {resourceEfficiencyTemp[0]};
				} else {
					resourceEfficiencyTemp = new int[] {resourceEfficiencyTemp[1]};
				}
			}
		} catch(IndexOutOfBoundsException e) {
			log.log(Level.FINE, "checkResourceEfficiencyLogic", e.getCause());
		}
		return resourceEfficiencyTemp;
	}
  
// 	createMap class end
}