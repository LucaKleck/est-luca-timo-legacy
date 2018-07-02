package mapTiles;

public class MapTileLightForest extends MapTile {
	private static final int TYPE = 2;
	private static final int[] TOWERTYPES = {0};
	private static final boolean TRAVERSABLE = true;
	public MapTileLightForest(int xPos, int yPos) {
		super(TYPE, xPos, yPos, TOWERTYPES, TRAVERSABLE);
	}


}
