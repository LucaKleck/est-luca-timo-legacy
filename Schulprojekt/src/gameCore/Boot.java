package gameCore;

/**
 * @Classname Boot
 * 
 * @version Alpha
 * 
 * @author Luca Kleck, Timo Bauermeister
 * 
 */
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Boot {
	public static Logger log;
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		log = Logger.getLogger("gameLog");  
	    FileHandler fh;  

	    try {  
	    	String path = System.getProperty("user.home") + File.separator + "Documents";
	    	path += File.separator + "EST-SCHULPROJEKT";
	    	File customDir = new File(path);

	    	if (customDir.exists()) {

	    	} else if (customDir.mkdirs()) {

	    	} else {

	    	}
	        // This block configure the logger with handler and formatter  
	        fh = new FileHandler(path+File.separator+"gameLog.log");
	       
	        log.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  

	        log.info("Log Start");  
	        log.setLevel(Level.WARNING);
	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }
		GameCoreController GCC = new GameCoreController();
		//objectMap.getTestMethod(10, 10);
	}
}
