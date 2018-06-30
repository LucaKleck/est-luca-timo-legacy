package mapTiles;

public class MapTilePlain extends MapTile {
	private static final int TYPE = 0;
	private static final int[] TOWERTYPES = {0};
	private static final boolean TRAVERSABLE = true;
	
	public MapTilePlain(int xPos, int yPos) {
		super(TYPE, xPos, yPos, TOWERTYPES, TRAVERSABLE);
	}


}
