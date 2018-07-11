package mapTiles;

import staticPackage.MapTileType;

public class MapTileJungle extends MapTile {
	private static final MapTileType TYPE = MapTileType.Jungle;
	private static final int[] TOWERTYPES = {0};
	private static final boolean TRAVERSABLE = true;
	public MapTileJungle(int xPos, int yPos, int[] resourceType, int[] resourceEfficiency) {
		super(TYPE, xPos, yPos, TOWERTYPES, TRAVERSABLE, resourceType, resourceEfficiency);
	}


}
