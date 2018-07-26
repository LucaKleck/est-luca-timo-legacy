package gameCore;

import java.io.File;

public class GameCoreController {
	private ResourceController resourceController;
	public GameCoreController() {
		this.resourceController = new ResourceController();
	}
	public GameCoreController(File path) {
		//this is the file for all the things to load from. File will be saveNUMBER.xml or something.
		
	}
	public ResourceController getResourceController() {
		return resourceController;
	}
	

}
