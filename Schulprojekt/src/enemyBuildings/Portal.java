package enemyBuildings;

import buildings.Building;
import info.ResourceType;
import mapTiles.MapTile;

public class Portal extends Building {
	private static final String buildingName = "Demon Portal";
	private static final ResourceType[] buildableOn = {};
	public Portal(MapTile mapTile, MapTile[][] map) {
		super(buildingName, buildableOn, mapTile);
		createBadBoy(map);
	}
	private void createBadBoy(MapTile[][] map) {
		
	}

}
