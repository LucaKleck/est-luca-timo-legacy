/**
 * @Classname boot
 * 
 * @version Alpha
 * 
 * @author Luca Kleck, Timo Bauermeister
 * 
 */
import javax.swing.JFrame;

public class boot {

	public static void main(String[] args) {
		ObjectMap objectMap = new ObjectMap();
		objectMap.printMap();
		JFrame f = new JFrame("Schulprojekt");
		ChangeJFrame.change(f,objectMap);
	}
}
