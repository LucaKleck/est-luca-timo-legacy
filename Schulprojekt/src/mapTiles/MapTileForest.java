package mapTiles;

import staticPackage.MapTileType;

public class MapTileForest extends MapTile {
	private static final MapTileType TYPE = MapTileType.Forest;
	private static final int[] TOWERTYPES = {0};
	private static final boolean TRAVERSABLE = false;
	public MapTileForest(int xPos, int yPos, int[] resourceType, int[] resourceEfficiency) {
		super(TYPE, xPos, yPos, TOWERTYPES, TRAVERSABLE, resourceType, resourceEfficiency);
	}
	public void TestMethod() {
		System.out.println("Tree");
	}

}
