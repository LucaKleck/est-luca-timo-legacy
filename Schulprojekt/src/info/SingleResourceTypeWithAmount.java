package info;
import info.ResourceType;
public class SingleResourceTypeWithAmount {
	private ResourceType resourceType;
	private int resourceAmount;
	public SingleResourceTypeWithAmount(ResourceType type, int amount) {
		this.resourceAmount = amount;
		this.resourceType = type;
	}
	public String toString() {
		String string = ""+resourceAmount;
		return string;
	}
	public int getResourceAmount() {
		return resourceAmount;
	}
	public ResourceType getResourceType() {
		return resourceType;
	}
	public void addResourceAmount(int amount) {
		this.resourceAmount += amount;
	}
	public void removeResourceAmount(int amount) {
		this.resourceAmount -= amount;
	}
}
