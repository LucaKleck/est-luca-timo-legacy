package mapTiles;

import info.ResourceType;
import staticPackage.MapTileType;

public class MapTileLightForest extends MapTile {
	private static final MapTileType TYPE = MapTileType.LightForest;
	private static final boolean TRAVERSABLE = true;
	public MapTileLightForest(int xPos, int yPos, ResourceType[] resourceType, int[] resourceEfficiency) {
		super(TYPE, xPos, yPos, TRAVERSABLE, resourceType, resourceEfficiency);
	}


}
