
public class map {
	private MapTile[][] map;
	
	public map(MapTile[][] finishedMap) {
		copyMap(finishedMap);
	}
	public MapTile[][] getMap() {
		return map;
	}
	private void copyMap(MapTile[][] finishedMap) {
		map = new MapTile[finishedMap.length][finishedMap[0].length];
		
		for(int y=0; y < map[0].length; y++) {
			for(int x=0; x < map.length; x++) {
				map[x][y] = finishedMap[x][y];
			}
		}
	}
}
