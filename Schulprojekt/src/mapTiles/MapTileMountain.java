package mapTiles;

import info.MapTileType;
import info.ResourceType;

public class MapTileMountain extends MapTileWithResources {
	private final static boolean traversable = true;
	private final static MapTileType type = MapTileType.Mountain;
	private final static ResourceType[] resourceType = { ResourceType.Stone,ResourceType.Metal };
	private final static int[] resourceEfficiency = {100,100};
	public MapTileMountain(int xPos, int yPos) {
		super(type, xPos, yPos, traversable, resourceType, resourceEfficiency);
	}

}
