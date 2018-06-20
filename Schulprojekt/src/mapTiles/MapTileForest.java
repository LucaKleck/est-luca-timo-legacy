package mapTiles;

public class MapTileForest extends MapTile {
	
	private static final int type = 1;
	public MapTileForest(int xPos, int yPos) {
		super(type, xPos, yPos);
	}
	public void TestMethod() {
		System.out.println("This is the output of the TestMethod");
	}
}
