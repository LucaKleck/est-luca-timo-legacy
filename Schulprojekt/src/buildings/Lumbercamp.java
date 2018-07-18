package buildings;

import mapTiles.MapTile;

public class Lumbercamp extends Building {
	private static int[] buildableOn = {1};
	public Lumbercamp(MapTile mapTile) {
		super("Lumbercamp", buildableOn, mapTile);
	}
}
