package mapTiles;

import staticPackage.MapTileType;

public class MapTilePlain extends MapTile {
	private static final MapTileType TYPE = MapTileType.Plain;
	private static final int[] TOWERTYPES = {0};
	private static final boolean TRAVERSABLE = true;
	public MapTilePlain(int xPos, int yPos, int[] resourceType, int[] resourceEfficiency) {
		super(TYPE, xPos, yPos, TOWERTYPES, TRAVERSABLE, resourceType, resourceEfficiency);
	}


}
