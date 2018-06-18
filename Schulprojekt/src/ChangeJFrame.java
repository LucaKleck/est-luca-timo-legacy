import javax.swing.JFrame;

public class ChangeJFrame {
	public static void change(JFrame f) {
		f.setSize(300,400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new DrawGui());
		f.setVisible(true);
	}
	
}
