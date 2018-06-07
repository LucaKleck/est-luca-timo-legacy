
public class map {
	private MapTile[][] map;
	
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
