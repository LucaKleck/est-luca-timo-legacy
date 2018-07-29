package mapTiles;

import info.MapTileType;
import info.ResourceType;

public class MapTileLightForest extends MapTileWithResources {
	private static final MapTileType TYPE = MapTileType.LightForest;
	private static final boolean TRAVERSABLE = true;
	public MapTileLightForest(int xPos, int yPos, ResourceType[] resourceType, int[] resourceEfficiency) {
		super(TYPE, xPos, yPos, TRAVERSABLE,resourceType,resourceEfficiency);
	}


}
