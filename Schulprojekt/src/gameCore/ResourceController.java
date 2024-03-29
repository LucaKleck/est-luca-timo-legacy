package gameCore;

import info.Resource;
import info.ResourceType;

public class ResourceController {
	private Resource money;
	private Resource food;
	private Resource wood;
	private Resource stone;
	private Resource metal;
	private Resource manaStone;
	
	public ResourceController() {
		money = new Resource(ResourceType.Money, 20000);
		food = new Resource(ResourceType.Food,20000);
		wood = new Resource(ResourceType.Wood, 20000);
		stone = new Resource(ResourceType.Stone, 20000);
		metal = new Resource(ResourceType.Metal, 20000);
		manaStone = new Resource(ResourceType.ManaStones, 20000);

	}
	// call this method once you have loaded it from a file and converted the strings into SingleResourceTypeWithAmount's
	public ResourceController(Resource money, Resource food, Resource wood, Resource stone, Resource metal, Resource manaStone) {
		this.money = money;
		this.food = food;
		this.wood = wood;
		this.stone = stone;
		this.metal = metal;
		this.manaStone = manaStone;
	}
	public String toString() {
		String string = "money: "+money+" food: "+food+" wood: "+wood+" stone: "+stone+" metal: "+metal+" manaStone: "+manaStone;
		return string;
	}
	public Resource[] getResources() {
		Resource[] resources = new Resource[6];
		resources[0] = money;
		resources[1] = food;
		resources[2] = wood;
		resources[3] = stone;
		resources[4] = metal;
		resources[5] = manaStone;
		return resources;
	}
	public Resource getMoney() {
		return money;
	}
	public Resource getFood() {
		return food;
	}
	public Resource getWood() {
		return wood;
	}
	public Resource getStone() {
		return stone;
	}
	public Resource getMetal() {
		return metal;
	}
	public Resource getManaStones() {
		return manaStone;
	}
	public boolean removeCost(int[] costs) {
		boolean hasResources = true;
		Resource[] resources = getResources();
		for( int x = 0; x < resources.length; x++) {
			if(resources[x].getResourceAmount() < costs[x]) {
				hasResources=false;
			}
		}
		if(hasResources) {			
			for(int maxFive = 0; maxFive < resources.length; maxFive++) {
				resources[maxFive].removeResourceAmount(costs[maxFive]); 
			}
		}
		return hasResources;
	}
}
