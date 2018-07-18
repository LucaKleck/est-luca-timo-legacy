package info;

import mapTiles.MapTileWithResources;

public class Item {

	private String itemName;
	private SingleResourceType moneyCost;
	private SingleResourceType foodCost;
	private SingleResourceType woodCost;
	private SingleResourceType stoneCost;
	private SingleResourceType metalCost;
	private SingleResourceType manaStoneCost;
	private ResourceType requiredType;
	
	public Item(String itemName) {
		switch(itemName) {
			case "ItemOne": this.itemName = "Lumbercamp";
							moneyCost = new SingleResourceType(ResourceType.Money,50);
							foodCost = new SingleResourceType(ResourceType.Food, 20);
							woodCost = new SingleResourceType(ResourceType.Wood, 10);
							stoneCost = new SingleResourceType(ResourceType.Stone, 40);
							metalCost = new SingleResourceType(ResourceType.Metal, 45);
							manaStoneCost = new SingleResourceType(ResourceType.ManaStones, 10);
							requiredType = ResourceType.Wood;
							break;
			case "TownHall":this.itemName = "Town Hall";
							moneyCost = new SingleResourceType(ResourceType.Money,0);
							foodCost = new SingleResourceType(ResourceType.Food, 0);
							woodCost = new SingleResourceType(ResourceType.Wood, 0);
							stoneCost = new SingleResourceType(ResourceType.Stone, 0);
							metalCost = new SingleResourceType(ResourceType.Metal, 0);
							manaStoneCost = new SingleResourceType(ResourceType.ManaStones, 0);
							requiredType = null;
			default: 		break;
		}
		/*
		 * DOIT REPLACE WITH ITEM ARRAY, THANKS!
		 */
	}
	public String toString() {
		String string;
		if(this.itemName != "Towns Hall") {
			string = "Name: " +itemName+"\nmoneyCost: " + moneyCost+"\nfoodCost: "+foodCost+"\nwoodCost: "+woodCost+"\nmetalCost: "+metalCost+"\nmanaStoneCost: "+manaStoneCost;
		} else {
			string = "Name: "+itemName;
		}
		return string;
	}
	public SingleResourceType[] getCosts() {
		SingleResourceType[] cost = new SingleResourceType[6];
		cost[0] = moneyCost;
		cost[1] = foodCost;
		cost[2] = woodCost;
		cost[3] = stoneCost;
		cost[4] = metalCost;
		cost[5] = manaStoneCost;
		return cost;
	}
	public boolean hasResources(ResourcesController resources, MapTileWithResources mapTile) {
		boolean hasResources = false;
		boolean hasRequiredType = false;
		for(int scan = 0; scan < mapTile.getResourceType().length; scan++) {
			if(mapTile.getResourceType()[scan].getType() == requiredType.getType()) {
				hasRequiredType = true;
			}
		}
		if(hasRequiredType&&hasSingleResource(resources.getResources()[0], getCosts()[0])&&hasSingleResource(resources.getResources()[1], getCosts()[1])&&hasSingleResource(resources.getResources()[2], getCosts()[2])&&hasSingleResource(resources.getResources()[3], getCosts()[3])&&hasSingleResource(resources.getResources()[4], getCosts()[4])&&hasSingleResource(resources.getResources()[5], getCosts()[5])   ) {
				hasResources = true;
		}
		return hasResources;
	}
	private boolean hasSingleResource(SingleResourceType resource, SingleResourceType cost) {
		boolean hasSingleResource = false;
		try {
			if(resource.getResourceAmount() >= cost.getResourceAmount()) hasSingleResource = true;
		} catch(Exception e) {
			System.out.println(e);
		}
		return hasSingleResource;
	}
	public String getItemName() {
		return itemName;
	}
	public SingleResourceType getMoneyCost() {
		return moneyCost;
	}
	public SingleResourceType getFoodCost() {
		return foodCost;
	}
	public SingleResourceType getWoodCost() {
		return woodCost;
	}
	public SingleResourceType getStoneCost() {
		return stoneCost;
	}
	public SingleResourceType getMetalCost() {
		return metalCost;
	}
	public SingleResourceType getManaStoneCost() {
		return manaStoneCost;
	}
}
