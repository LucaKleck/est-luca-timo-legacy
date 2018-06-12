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
		ObjectMap map = new ObjectMap();
		map.printMap();
		JFrame f = new JFrame("Schulprojekt");
		ChangeJFrame.change(f);
	}
}
