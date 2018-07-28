package buildings;

import gameCore.ResourceController;
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
	public FishingDock(MapTileWithResources mapTile) {
		super("Fishing Dock", buildableOn, mapTile, resourceFromBuilding);
		super.setBaseResourceAmount(mapTile);
	}
	@Override
	protected void levelThree(ResourceController resources) {
		int[] costs = {100,100,100,100,100,100};
		resources.removeCost(costs);
	}
	@Override
	protected void levelTwo(ResourceController resources) {
		int[] costs = {100,100,100,100,100,100};
		resources.removeCost(costs);
		
	}
	@Override
	protected void levelOne(ResourceController resources) {
		int[] costs = {100,100,100,100,100,100};
		resources.removeCost(costs);
	}
}
