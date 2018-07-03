package mapTiles;

public class MapTileForest extends MapTile {
	private static final int TYPE = 1;
	private static final int[] TOWERTYPES = {0};
	private static final boolean TRAVERSABLE = false;
	public MapTileForest(int xPos, int yPos, int[] resourceType, int[] resourceEfficiency) {
		super(TYPE, xPos, yPos, TOWERTYPES, TRAVERSABLE, resourceType, resourceEfficiency);
	}
	public void TestMethod() {
		System.out.println("Tree");
	}

}
