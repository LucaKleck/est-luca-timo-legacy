package mapTiles;

import staticPackage.MapTileType;

public class MapTileRiver extends MapTile {
	private static final MapTileType TYPE = MapTileType.River;
	private static final int[] TOWERTYPES = {1};
	private static final boolean TRAVERSABLE = false;
	public MapTileRiver(int xPos, int yPos, int[] resourceType, int[] resourceEfficiency) {
		super(TYPE, xPos, yPos, TOWERTYPES, TRAVERSABLE, resourceType, resourceEfficiency);
	}

}
