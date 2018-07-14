package mapTiles;

import staticPackage.MapTileType;

public class MapTileForest extends MapTile {
	private static final MapTileType TYPE = MapTileType.Forest;
	private static final boolean TRAVERSABLE = false;
	public MapTileForest(int xPos, int yPos, int[] resourceType, int[] resourceEfficiency) {
		super(TYPE, xPos, yPos, TRAVERSABLE, resourceType, resourceEfficiency);
	}
	public void TestMethod() {
		System.out.println("Tree");
	}

}
