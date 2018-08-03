package gameCore;

import java.io.File;
import java.util.logging.Logger;

import framePackage.MainJFrame;

public class GameCoreController {
	public static Logger log = Boot.log;
	public static ResourceController resourceController;
	public static GameCoreController GCC;
	public static GameCommandHandler GCH;
	public static MainJFrame mainJFrame;
	public static ObjectMap objectMap;
	public static int roundNumber;
	
	public GameCoreController() {
		GCC = this;
		objectMap = new ObjectMap();
		resourceController = new ResourceController();
		mainJFrame = new MainJFrame();
		GCH = new GameCommandHandler();
		GameCoreController.roundNumber = 0;
	}
	public GameCoreController(File path) {
		//this is the file for all the things to load from. File will be saveNUMBER.xml or something.
		//TODO add file reader
	}
	public void nextTurn() {
		GameCoreController.roundNumber++;
	}

}
