
public class map {
	/*
	 * Class map is for loading an existing map or creating a new one, then having special functions related to map movement or editing.
	 */
	private MapTile[][] map;
	public map() {
		copyMap(createMap.createDefault(map));
	}
	public map(MapTile[][] tempMap) {
		copyMap(tempMap);
	}
	public MapTile[][] getMap() {
		return map;
	}
	private void copyMap(MapTile[][] tempMap) {
		map = new MapTile[tempMap.length][tempMap[0].length];
		
		for(int y=0; y < map[0].length; y++) {
			for(int x=0; x < map.length; x++) {
				map[x][y] = tempMap[x][y];
			}
		}
	}
}
