package info;

public enum Item {
	itemOne("itemOne");
	
	private String itemName;
	private SingleResourceType moneyCost;
	private SingleResourceType foodCost;
	private SingleResourceType woodCost;
	private SingleResourceType stoneCost;
	private SingleResourceType metalCost;
	private SingleResourceType manaStoneCost;
	
	Item(String itemName) {
		this.itemName = itemName;
		switch(itemName) {
			case "itemOne": moneyCost = new SingleResourceType(ResourceTypes.Money,50);
							foodCost = new SingleResourceType(ResourceTypes.Food, 20);
							woodCost = new SingleResourceType(ResourceTypes.Wood, 10);
							stoneCost = new SingleResourceType(ResourceTypes.Stone, 40);
							metalCost = new SingleResourceType(ResourceTypes.Metal, 45);
							manaStoneCost = new SingleResourceType(ResourceTypes.ManaStones, 10);
							break;
			default: 		break;
		}
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
