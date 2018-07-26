package buildings;

import gameCore.ResourceController;
import info.ResourceType;
import mapTiles.MapTile;

public abstract class BuildingWithResources extends Building {
	private ResourceType resourceFromBuilding;
	private int baseResourceAmount = 100;
	private int level = 1;
	public BuildingWithResources(String buildingName, ResourceType[] buildableOn, MapTile mapTile, ResourceType resourceFromBuilding) {
		super(buildingName, buildableOn, mapTile);
		this.resourceFromBuilding = resourceFromBuilding;
	}
	public void levelUp(ResourceController resources) {
		switch(this.level) {
			case 1:
				
				break;
		}
	}
	
	
	
	
	/*
	 * 
	 * getter
	 * 
	 */
	public ResourceType getResourceFromBuilding() {
		return resourceFromBuilding;
	}
	public int getBaseResourceAmount() {
		return baseResourceAmount;
	}
	public int getLevel() {
		return level;
	}

}
