package buildings;

import info.ResourceType;
import mapTiles.MapTileWithResources;

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
	private static int[] levelCost = {100,100,100,100,100,100};
	public FishingDock(MapTileWithResources mapTile) {
		super("Fishing Dock", buildableOn, mapTile, resourceFromBuilding, levelCost);
	}
}
