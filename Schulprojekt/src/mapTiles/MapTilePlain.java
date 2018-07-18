package mapTiles;

import info.MapTileType;
import info.ResourceType;

public class MapTilePlain extends MapTileWithResources {
	private static final MapTileType TYPE = MapTileType.Plain;
	private static final boolean TRAVERSABLE = true;
	public MapTilePlain(int xPos, int yPos, ResourceType[] resourceType, int[] resourceEfficiency) {
		super(TYPE, xPos, yPos, TRAVERSABLE, resourceType, resourceEfficiency);
	}


}
