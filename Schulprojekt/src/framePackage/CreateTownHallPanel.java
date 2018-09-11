package framePackage;

import javax.swing.JButton;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class CreateTownHallPanel extends JPanel {
	private static final long serialVersionUID = 4173647531503859006L;
	
	private JButton btnTownsHall;
	@SuppressWarnings("unused")
	private MainJFrame mainJFrame;
	private boolean selected = false;
	
	public CreateTownHallPanel(MainJFrame mainJFrame) {
		this.mainJFrame = mainJFrame;
		setLayout(new MigLayout("", "[100%]", "[100%]"));
		btnTownsHall = new JButton("Town Hall");
		btnTownsHall.setActionCommand("townHallToggle");
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
