package mapTiles;

import info.MapTileType;
import info.ResourceType;

public class MapTileForest extends MapTileWithResources {
	private static final MapTileType TYPE = MapTileType.Forest;
	private static final boolean TRAVERSABLE = true;
	public MapTileForest(int xPos, int yPos, ResourceType[] resourceType, int[] resourceEfficiency) {
		super(TYPE, xPos, yPos, TRAVERSABLE,resourceType,resourceEfficiency);
	}
	public void TestMethod() {
		System.out.println("Tree");
	}

}
