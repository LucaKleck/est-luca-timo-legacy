package mapTiles;

public class MapTileForest extends MapTile {
	
	private static final int type = 1;
	public MapTileForest(int xPos, int yPos) {
		super(xPos, yPos);
	}

	public MapTileForest(String name, int xPos, int yPos) {
		super(type, name, xPos, yPos);
	}

	public MapTileForest(int type, int xPos, int yPos) {
		super(type, xPos, yPos);
	}

}
