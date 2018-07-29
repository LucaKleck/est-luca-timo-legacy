package buildings;

import gameCore.ResourceController;
import info.ResourceType;
import mapTiles.MapTile;
import mapTiles.MapTileWithResources;

public abstract class BuildingWithResources extends Building {
	private ResourceType resourceFromBuilding;
	private int baseResourceAmount;
	private int level = 1;
	public BuildingWithResources(String buildingName, ResourceType[] buildableOn, MapTile mapTile, ResourceType resourceFromBuilding) {
		super(buildingName, buildableOn, mapTile);
		this.baseResourceAmount = 100;
		this.resourceFromBuilding = resourceFromBuilding;
	}
	protected void setBaseResourceAmount(MapTileWithResources mapTile) {
		if(mapTile.getResourceType()[0]==resourceFromBuilding) {
			baseResourceAmount = (int)(mapTile.getResourceEfficiency()[0]/100*100+100);
		}
		try {
			if(mapTile.getResourceType()[1]==resourceFromBuilding) {
				baseResourceAmount = (int)(mapTile.getResourceEfficiency()[1]/100*100+100);
			}
		} catch(IndexOutOfBoundsException e) {
		}
		setBaseResourceAmount(baseResourceAmount);
	}
	protected void setBaseResourceAmount(int baseResourceAmount) {
		this.baseResourceAmount = baseResourceAmount;
	}
	public void levelUp(ResourceController resources) {
		switch(this.level) {
			case 1: levelOne(resources);
					level++;
			break;
			case 2: levelTwo(resources);
					level++;
			break;
			case 3: levelThree(resources);
					level++;
			break;
			case 4: System.out.println("Level "+this.getLevel()+" is max level");
			break;
		}
	}
	// these methods need to be overridden by each building.
	protected void levelThree(ResourceController resources) {
		
	}
	protected void levelTwo(ResourceController resources) {
		
	}
	protected void levelOne(ResourceController resources) {
		
	}
	/*
	 * 
	 * getter
	 * 
	 */
	public int getResourcePerRound() {
		return this.level*this.baseResourceAmount;
	}
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
