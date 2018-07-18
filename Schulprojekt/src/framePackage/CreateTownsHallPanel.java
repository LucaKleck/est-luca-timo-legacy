package framePackage;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class CreateTownsHallPanel extends JPanel {
	private static final long serialVersionUID = 4173647531503859006L;
	
	private JButton btnTownsHall;
	@SuppressWarnings("unused")
	private MainJFrame mainJFrame;
	private boolean selected = false;
	
	public CreateTownsHallPanel(MainJFrame mainJFrame) {
		this.mainJFrame = mainJFrame;
		setLayout(new MigLayout("", "[100%]", "[100%]"));
		btnTownsHall = new JButton("Towns Hall");
		btnTownsHall.setActionCommand("townsHallToggle");
		btnTownsHall.addActionListener(mainJFrame);
		this.add(btnTownsHall, "cell 0 0,grow");
	}
	public boolean getSelected() {
		return selected;
	}
	public void toggleSelected() {
		this.selected =!selected;
	}
	
}
