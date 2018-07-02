package mapTiles;

public class MapTileJungle extends MapTile {
	private static final int TYPE = 3;
	private static final int[] TOWERTYPES = {0};
	private static final boolean TRAVERSABLE = true;
	public MapTileJungle(int xPos, int yPos) {
		super(TYPE, xPos, yPos, TOWERTYPES, TRAVERSABLE);
	}


}
