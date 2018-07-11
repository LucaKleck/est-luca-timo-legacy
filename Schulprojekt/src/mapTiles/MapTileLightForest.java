package mapTiles;

import staticPackage.MapTileType;

public class MapTileLightForest extends MapTile {
	private static final MapTileType TYPE = MapTileType.LightForest;
	private static final int[] TOWERTYPES = {0};
	private static final boolean TRAVERSABLE = true;
	public MapTileLightForest(int xPos, int yPos, int[] resourceType, int[] resourceEfficiency) {
		super(TYPE, xPos, yPos, TOWERTYPES, TRAVERSABLE, resourceType, resourceEfficiency);
	}


}
