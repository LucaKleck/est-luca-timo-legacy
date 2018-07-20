package buildings;

import info.ResourceType;
import mapTiles.MapTile;

public class BuildingWithResources extends Building {
	private ResourceType resourceFromBuilding;
	public BuildingWithResources(String buildingName, ResourceType[] buildableOn, MapTile mapTile, ResourceType resourceFromBuilding) {
		super(buildingName, buildableOn, mapTile);
		this.resourceFromBuilding = resourceFromBuilding;
	}
	public ResourceType getResourceFromBuilding() {
		return resourceFromBuilding;
	}

}
