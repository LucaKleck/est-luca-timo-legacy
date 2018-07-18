package mapTiles;

import info.MapTileType;
import info.ResourceType;

public abstract class MapTileWithResources extends MapTile {
	private ResourceType[] resourceType;
	private int[] resourceEfficiency;

	public MapTileWithResources(MapTileType type, int xPos, int yPos, boolean traversable, ResourceType[] resourceType, int[] resourceEfficiency) {
		super(type, xPos, yPos, traversable);
		this.resourceType = resourceType;
		this.resourceEfficiency = resourceEfficiency;
	}
	public String toString() {
		String str = "";
		try {
			str = "MapTile" + super.getMapTileType().getName() + "\nxPos: " + super.getXPos() + " yPos: " + super.getYPos() + "\nbuilding: " + super.getBuilding()+"\nresourceType(s): "+resourceType[0]+": "+resourceEfficiency[0]+"%, "+resourceType[1]+": "+resourceEfficiency[1]+"%";
		} catch(IndexOutOfBoundsException e) {
			str = "MapTile" + super.getMapTileType().getName() + "\nxPos: " + super.getXPos() + " yPos: " + super.getYPos() + "\nbuilding: " + super.getBuilding()+"\nresourceType(s): "+resourceType[0]+": "+resourceEfficiency[0]+"%";
		}
		return str;
	}
	public ResourceType[] getResourceType() {
		return resourceType;
	}
	public int[] getResourceEfficiency() {
		return resourceEfficiency;
	}
}
