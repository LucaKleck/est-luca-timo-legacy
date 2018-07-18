package mapTiles;

import info.MapTileType;
import info.ResourceType;

public class MapTileRiver extends MapTileWithResources {
	private static final MapTileType TYPE = MapTileType.River;
	private static final boolean TRAVERSABLE = false;
	public MapTileRiver(int xPos, int yPos, ResourceType[] resourceType, int[] resourceEfficiency) {
		super(TYPE, xPos, yPos, TRAVERSABLE, resourceType, resourceEfficiency);
	}

}
