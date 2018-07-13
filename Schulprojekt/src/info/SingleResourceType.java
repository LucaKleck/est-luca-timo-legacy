package info;
import info.ResourceTypes;
public class SingleResourceType {
	private ResourceTypes resourceType;
	private int resourceAmount;
	public SingleResourceType(ResourceTypes type, int amount) {
		this.resourceAmount = amount;
		this.resourceType = type;
	}
	public int getResourceAmount() {
		return resourceAmount;
	}
	public ResourceTypes getResourceType() {
		return resourceType;
	}
	public void addResourceAmount(int amount) {
		this.resourceAmount += amount;
	}
	public void removeResourceAmount(int amount) {
		this.resourceAmount -= amount;
	}
}
