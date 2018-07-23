package buildings;

import info.ResourceType;
import mapTiles.MapTile;

public class FishingDock extends BuildingWithResources {
	/*
	 * buildableOn:
	 * 0 = money
	 * 1 = food
	 * 2 = wood
	 * 3 = stone
	 * 4 = metal
	 * 5 = manaStone
	 */
	private static ResourceType[] buildableOn = {ResourceType.Wood};
	private static ResourceType resourceFromBuilding = ResourceType.Wood;
	public FishingDock(MapTile mapTile) {
		super("Fishing Dock", buildableOn, mapTile, resourceFromBuilding);
	}

}
