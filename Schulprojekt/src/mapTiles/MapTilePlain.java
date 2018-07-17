package mapTiles;

import info.ResourceType;
import staticPackage.MapTileType;

public class MapTilePlain extends MapTile {
	private static final MapTileType TYPE = MapTileType.Plain;
	private static final boolean TRAVERSABLE = true;
	public MapTilePlain(int xPos, int yPos, ResourceType[] resourceType, int[] resourceEfficiency) {
		super(TYPE, xPos, yPos, TRAVERSABLE, resourceType, resourceEfficiency);
	}


}
