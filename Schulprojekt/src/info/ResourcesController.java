package info;

public class ResourcesController {
	private SingleResourceType money;
	private SingleResourceType food;
	private SingleResourceType wood;
	private SingleResourceType stone;
	private SingleResourceType metal;
	private SingleResourceType manaStone;
	public ResourcesController() {
		money = new SingleResourceType(ResourceTypes.Money, 200);
		food = new SingleResourceType(ResourceTypes.Food,200);
		wood = new SingleResourceType(ResourceTypes.Wood, 200);
		stone = new SingleResourceType(ResourceTypes.Stone, 200);
		metal = new SingleResourceType(ResourceTypes.Metal, 200);
		manaStone = new SingleResourceType(ResourceTypes.ManaStones, 200);
	}
	public String toString() {
		String string = "money: "+money+" food: "+food+" wood: "+wood+" stone: "+stone+" metal: "+metal+" manaStone: "+manaStone;
		return string;
	}
	public SingleResourceType[] getResources() {
		SingleResourceType[] resources = new SingleResourceType[6];
		resources[0] = money;
		resources[1] = food;
		resources[2] = wood;
		resources[3] = stone;
		resources[4] = metal;
		resources[5] = manaStone;
		return resources;
	}
	public SingleResourceType getMoney() {
		return money;
	}
	public SingleResourceType getFood() {
		return food;
	}
	public SingleResourceType getWood() {
		return wood;
	}
	public SingleResourceType getStone() {
		return stone;
	}
	public SingleResourceType getMetal() {
		return metal;
	}
	public SingleResourceType getManaStones() {
		return manaStone;
	}
}
