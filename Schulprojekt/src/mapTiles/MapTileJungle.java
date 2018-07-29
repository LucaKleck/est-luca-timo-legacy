package mapTiles;

import info.MapTileType;
import info.ResourceType;

public class MapTileJungle extends MapTileWithResources {
	private static final MapTileType TYPE = MapTileType.Jungle;
	private static final boolean TRAVERSABLE = true;
	
	public MapTileJungle(int xPos, int yPos, ResourceType[] resourceType, int[] resourceEfficiency) {
		super(TYPE, xPos, yPos, TRAVERSABLE,resourceType,resourceEfficiency);
	}


}
