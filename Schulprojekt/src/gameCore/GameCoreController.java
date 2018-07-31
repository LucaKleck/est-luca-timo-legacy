package gameCore;

import java.io.File;

import framePackage.MainJFrame;

public class GameCoreController {
	private ResourceController resourceController;
	private MainJFrame mainJFrame;
	
	
	public GameCoreController(MainJFrame mainJFrame) {
		this.mainJFrame = mainJFrame;
		this.resourceController = new ResourceController(this.mainJFrame);
	}
	public GameCoreController(File path) {
		//this is the file for all the things to load from. File will be saveNUMBER.xml or something.
		
	}
	public ResourceController getResourceController() {
		return resourceController;
	}
	

}
