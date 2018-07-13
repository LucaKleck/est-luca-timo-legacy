/**
 * @Classname Boot
 * 
 * @version Alpha
 * 
 * @author Luca Kleck, Timo Bauermeister
 * 
 */
import javax.swing.JFrame;

import framePackage.MainJFrame;
import info.ResourcesController;
import staticPackage.ObjectMap;

public class Boot {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		ObjectMap objectMap = new ObjectMap();
		//objectMap.printMap();
		ResourcesController resources = new ResourcesController();
		JFrame f = new MainJFrame(objectMap, resources);
		//objectMap.getTestMethod(10, 10);
	}
}
