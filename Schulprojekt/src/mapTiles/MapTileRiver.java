package mapTiles;

public class MapTileRiver extends MapTile {
	private static final int TYPE = 20;
	private static final int[] TOWERTYPES = {1};
	private static final boolean TRAVERSABLE = false;
	public MapTileRiver(int xPos, int yPos) {
		super(TYPE, xPos, yPos, TOWERTYPES, TRAVERSABLE);
	}

}
