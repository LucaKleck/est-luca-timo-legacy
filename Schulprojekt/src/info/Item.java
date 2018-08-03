package info;

import gameCore.ResourceController;
import mapTiles.MapTileWithResources;

public class Item {

	private String itemName;
	
	private Resource moneyCost;
	private Resource foodCost;
	private Resource woodCost;
	private Resource stoneCost;
	private Resource metalCost;
	private Resource manaStoneCost;
	private ResourceType requiredType;
	
	public Item(String itemName) {
		switch(itemName) {
			case "buyTownHall,Building"	: setToTownHall();
			break;
			case "buyItemOne,Building"	: setToLumbercamp();
			break;
			case "buyItemTwo,Building"	: setToFishingDock();
			break;
		}
	}
	public String toString() {
		String string;
		if(this.itemName != "Town Hall") {
			string = "Name: " +itemName+"\nmoneyCost: " + moneyCost+"\nfoodCost: "+foodCost+"\nwoodCost: "+woodCost+"\nmetalCost: "+metalCost+"\nmanaStoneCost: "+manaStoneCost;
		} else {
			string = "Name: "+itemName;
		}
		return string;
	}
	public Resource[] getCost() {
		Resource[] cost = new Resource[6];
		cost[0] = moneyCost;
		cost[1] = foodCost;
		cost[2] = woodCost;
		cost[3] = stoneCost;
		cost[4] = metalCost;
		cost[5] = manaStoneCost;
		return cost;
	}
	public boolean hasResources(ResourceController resources, MapTileWithResources mapTile) {
		boolean hasResources = false;
		boolean hasRequiredType = false;
		if(requiredType == null) {
			hasRequiredType = true;
		} else {
			for(int scan = 0; scan < mapTile.getResourceType().length; scan++) {
				if(mapTile.getResourceType()[scan].getType() == requiredType.getType()) {
					hasRequiredType = true;
				}
			}
		}
		if(hasRequiredType&&hasSingleResource(resources.getResources()[0], getCost()[0])&&hasSingleResource(resources.getResources()[1], getCost()[1])&&hasSingleResource(resources.getResources()[2], getCost()[2])&&hasSingleResource(resources.getResources()[3], getCost()[3])&&hasSingleResource(resources.getResources()[4], getCost()[4])&&hasSingleResource(resources.getResources()[5], getCost()[5])   ) {
				hasResources = true;
		}
		return hasResources;
	}
	private boolean hasSingleResource(Resource resource, Resource cost) {
		boolean hasSingleResource = false;
		try {
			if(resource.getResourceAmount() >= cost.getResourceAmount()) hasSingleResource = true;
		} catch(Exception e) {
			System.out.println(e);
		}
		return hasSingleResource;
	}
	//
	private void setToTownHall() {
		this.itemName = "Town Hall";
		moneyCost = new Resource(ResourceType.Money,0);
		foodCost = new Resource(ResourceType.Food, 0);
		woodCost = new Resource(ResourceType.Wood, 0);
		stoneCost = new Resource(ResourceType.Stone, 0);
		metalCost = new Resource(ResourceType.Metal, 0);
		manaStoneCost = new Resource(ResourceType.ManaStones, 0);
		requiredType = null;	
	}
	private void setToLumbercamp() {
		this.itemName = "Lumbercamp";
		moneyCost = new Resource(ResourceType.Money,50);
		foodCost = new Resource(ResourceType.Food, 20);
		woodCost = new Resource(ResourceType.Wood, 10);
		stoneCost = new Resource(ResourceType.Stone, 40);
		metalCost = new Resource(ResourceType.Metal, 45);
		manaStoneCost = new Resource(ResourceType.ManaStones, 10);
		requiredType = ResourceType.Wood;
	}
	private void setToFishingDock() {
		this.itemName = "Fishing Dock";
		moneyCost = new Resource(ResourceType.Money,50);
		foodCost = new Resource(ResourceType.Food, 20);
		woodCost = new Resource(ResourceType.Wood, 10);
		stoneCost = new Resource(ResourceType.Stone, 40);
		metalCost = new Resource(ResourceType.Metal, 45);
		manaStoneCost = new Resource(ResourceType.ManaStones, 10);
		requiredType = ResourceType.Food;
	}
	// Getter
	public String getItemName() {
		return itemName;
	}
	public Resource getMoneyCost() {
		return moneyCost;
	}
	public Resource getFoodCost() {
		return foodCost;
	}
	public Resource getWoodCost() {
		return woodCost;
	}
	public Resource getStoneCost() {
		return stoneCost;
	}
	public Resource getMetalCost() {
		return metalCost;
	}
	public Resource getManaStoneCost() {
		return manaStoneCost;
	}
}
