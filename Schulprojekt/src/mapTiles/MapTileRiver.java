package mapTiles;

import info.ResourceType;
import staticPackage.MapTileType;

public class MapTileRiver extends MapTile {
	private static final MapTileType TYPE = MapTileType.River;
	private static final boolean TRAVERSABLE = false;
	public MapTileRiver(int xPos, int yPos, ResourceType[] resourceType, int[] resourceEfficiency) {
		super(TYPE, xPos, yPos, TRAVERSABLE, resourceType, resourceEfficiency);
	}

}
