package gameCore;

import info.ResourceType;
import info.SingleResourceTypeWithAmount;

public class ResourcesController {
	private SingleResourceTypeWithAmount money;
	private SingleResourceTypeWithAmount food;
	private SingleResourceTypeWithAmount wood;
	private SingleResourceTypeWithAmount stone;
	private SingleResourceTypeWithAmount metal;
	private SingleResourceTypeWithAmount manaStone;
	public ResourcesController() {
		money = new SingleResourceTypeWithAmount(ResourceType.Money, 200);
		food = new SingleResourceTypeWithAmount(ResourceType.Food,200);
		wood = new SingleResourceTypeWithAmount(ResourceType.Wood, 200);
		stone = new SingleResourceTypeWithAmount(ResourceType.Stone, 200);
		metal = new SingleResourceTypeWithAmount(ResourceType.Metal, 200);
		manaStone = new SingleResourceTypeWithAmount(ResourceType.ManaStones, 200);
	}
	public String toString() {
		String string = "money: "+money+" food: "+food+" wood: "+wood+" stone: "+stone+" metal: "+metal+" manaStone: "+manaStone;
		return string;
	}
	public SingleResourceTypeWithAmount[] getResources() {
		SingleResourceTypeWithAmount[] resources = new SingleResourceTypeWithAmount[6];
		resources[0] = money;
		resources[1] = food;
		resources[2] = wood;
		resources[3] = stone;
		resources[4] = metal;
		resources[5] = manaStone;
		return resources;
	}
	public SingleResourceTypeWithAmount getMoney() {
		return money;
	}
	public SingleResourceTypeWithAmount getFood() {
		return food;
	}
	public SingleResourceTypeWithAmount getWood() {
		return wood;
	}
	public SingleResourceTypeWithAmount getStone() {
		return stone;
	}
	public SingleResourceTypeWithAmount getMetal() {
		return metal;
	}
	public SingleResourceTypeWithAmount getManaStones() {
		return manaStone;
	}
}
