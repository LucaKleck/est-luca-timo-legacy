/**
 * @Classname Boot
 * 
 * @version Alpha
 * 
 * @author Luca Kleck, Timo Bauermeister
 * 
 */
import java.util.logging.Level;

import javax.swing.JFrame;

import com.sun.istack.internal.logging.Logger;

import framePackage.MainJFrame;
import staticPackage.ObjectMap;

public class Boot {
	private static final Logger log = Logger.getLogger(Boot.class.getName(), Boot.class);
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		log.setLevel(Level.ALL);
		ObjectMap objectMap = new ObjectMap();		
		//objectMap.printMap();
		JFrame f = new MainJFrame(objectMap);
		//objectMap.getTestMethod(10, 10);
	}
}
