package staticPackage;
import mapTiles.MapTile;
import mapTiles.MapTileForest;
import units.Unit;

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
	@SuppressWarnings("unused")
	private Unit[][][] unitMap; 
	private int width;
	private int height;
	// Constructors
	public ObjectMap() {
		this.width = 49;
		this.height = 49;
		copyMap(CreateMap.createCustom(width,height));
	}
	public ObjectMap(int width, int height) {
		this.width = width;
		this.height = height;
		copyMap(CreateMap.createCustom(width,height));
		
	}
	public ObjectMap(MapTile[][] loadMap) {
		copyMap(loadMap);
		this.width = loadMap.length;
		this.height = loadMap[0].length;
	}
	// Methods public
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
	public void getTestMethod(int x, int y) {
		if(map[x][y].getClass().equals(MapTileForest.class)) ((MapTileForest) map[x][y]).TestMethod();
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
