package buildings;

import gameCore.ResourceController;
import info.ResourceType;
import mapTiles.MapTileWithResources;

public abstract class BuildingWithResources extends Building {
	private ResourceType resourceFromBuilding;
	private int baseResourceAmount;
	private int lastLevel = 1;
	private int level = 1;
	private int[] levelCost;
	public BuildingWithResources(String buildingName, ResourceType[] buildableOn, MapTileWithResources mapTile, ResourceType resourceFromBuilding, int[] levelBaseCost) {
		super(buildingName, buildableOn, mapTile);
		setBaseResourceAmount(mapTile);
		this.levelCost = levelBaseCost;
		this.resourceFromBuilding = resourceFromBuilding;
	}
	private void setLevelCost() {
		if(lastLevel!=level) {
			this.levelCost = new int[] {levelCost[0]*this.level,levelCost[1]*this.level,levelCost[2]*this.level,levelCost[3]*this.level,levelCost[4]*this.level,levelCost[5]*this.level};
		}
	}
	public String levelCostToString() {
		String s = "money: "+levelCost[0]+" food: "+levelCost[1]+" wood: "+levelCost[2]+" stone: "+levelCost[3]+" metal: "+levelCost[4]+" manaStone: "+levelCost[5];
		return s;
	}
	private void setBaseResourceAmount(MapTileWithResources mapTile) {
		if(mapTile.getResourceType()[0]==resourceFromBuilding) {
			baseResourceAmount = (int)(mapTile.getResourceEfficiency()[0]/100*100+100);
		}
		try {
			if(mapTile.getResourceType()[1]==resourceFromBuilding) {
				baseResourceAmount = (int)(mapTile.getResourceEfficiency()[1]/100*100+100);
			}
		} catch(IndexOutOfBoundsException e) {
		}
	}
	public void levelUp(ResourceController resources) {
		lastLevel = level;
		boolean hasResources = resources.removeCost(levelCost);
		if(hasResources) {
			level++;
			setLevelCost();
			System.out.println("level: "+this.level);
		}
	}
	/*
	 * 
	 * getter
	 * 
	 */
	public int getResourcePerRound() {
		return this.level*2*this.baseResourceAmount;
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
