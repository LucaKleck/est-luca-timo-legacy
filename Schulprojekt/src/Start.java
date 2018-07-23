
/**
 * @Classname Boot
 * 
 * @version Alpha
 * 
 * @author Luca Kleck, Timo Bauermeister
 * 
 */
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JFrame;

import framePackage.MainJFrame;

public class Start {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Logger logger = Logger.getLogger("gameLog");  
	    FileHandler fh;  

	    try {  
	        // This block configure the logger with handler and formatter  
	        fh = new FileHandler("C:/Users/Markus/AppData/Roaming/Schulprojekt/gameLog.log");  
	        logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  

	        logger.info("Log Start");  
	        logger.setLevel(Level.ALL);
	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
		JFrame f = new MainJFrame(logger);
		//objectMap.getTestMethod(10, 10);
	}
}
