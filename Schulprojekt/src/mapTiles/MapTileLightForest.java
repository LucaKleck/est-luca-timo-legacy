package mapTiles;

import staticPackage.MapTileType;

public class MapTileLightForest extends MapTile {
	private static final MapTileType TYPE = MapTileType.LightForest;
	private static final boolean TRAVERSABLE = true;
	public MapTileLightForest(int xPos, int yPos, int[] resourceType, int[] resourceEfficiency) {
		super(TYPE, xPos, yPos, TRAVERSABLE, resourceType, resourceEfficiency);
	}


}
