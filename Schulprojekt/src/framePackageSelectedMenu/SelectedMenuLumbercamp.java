package framePackageSelectedMenu;

import javax.swing.JPanel;

import buildings.BuildingWithResources;
import framePackage.MainJFrame;
import net.miginfocom.swing.MigLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SelectedMenuLumbercamp extends JPanel implements ActionListener {
	private static final long serialVersionUID = 9089408413515547169L;
	private MainJFrame mainJFrame;
	public SelectedMenuLumbercamp(MainJFrame mainJFrame) {
		 this.mainJFrame = mainJFrame;
		 setLayout(new MigLayout("", "[100%,center]", "[10%,top]"));
		 
		 JButton btnUpgrade = new JButton("Upgrade");
		 btnUpgrade.addActionListener(this);
		 add(btnUpgrade, "cell 0 0,grow");
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		if(evt.getActionCommand() == "Upgrade") {
			BuildingWithResources building = (BuildingWithResources)mainJFrame.getDrawMap().getSelectedMapTile().getBuilding();
			building.levelUp(mainJFrame.getResource());
			System.out.println("Level: "+building.getLevel());
		}
	}
	
}
