package framePackageSelectedMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import buildings.BuildingWithResources;
import framePackage.MainJFrame;
import gameCore.GameCoreController;
import net.miginfocom.swing.MigLayout;

public abstract class SelectedMenu extends JPanel implements ActionListener {
	private static final long serialVersionUID = 9089408413515547169L;
	protected MainJFrame mainJFrame;
	protected static JButton btnUpgrade;
	protected static UpdateToolTip utt;
	protected static Timer t;
	protected class UpdateToolTip implements Runnable {

		@Override
		public void run() {
			t = new Timer(1000, new UpdateAction());
			t.start();
			t.setRepeats(true);
		}
		protected class UpdateAction implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent evt) {
				try {
					if((BuildingWithResources)mainJFrame.getDrawMap().getSelectedMapTile().getBuilding() == null) {
						t.stop();
						utt = null;
					}
				} catch (ClassCastException e) {
					t.stop();
					utt = null;
				}
				try {
					BuildingWithResources building = (BuildingWithResources)mainJFrame.getDrawMap().getSelectedMapTile().getBuilding();
					btnUpgrade.setToolTipText("Next Level: "+(building.getLevel()+1)+"\nCost:"+building.levelCostToString());
				} catch(NullPointerException e) {
				} catch(ClassCastException e1) {
				}
			}
			
		}

	}
	public SelectedMenu(MainJFrame mainJFrame) {
		 this.mainJFrame = mainJFrame;
		 setLayout(new MigLayout("", "[100%,center]", "[10%,top]"));
		 upgradeButton();
		 //TODO add info sheet in textpane or something then that can be used for all the other stuff
		 if(utt == null) {			 
			 utt = new UpdateToolTip();
			 utt.run();
		 }
	}
	protected void upgradeButton() {
		 btnUpgrade = new JButton("Upgrade");
		 btnUpgrade.addActionListener(this);
		 add(btnUpgrade, "cell 0 0,grow");
	}
	@Override
	public void actionPerformed(ActionEvent evt) {
		try {
			if(evt.getActionCommand() == "Upgrade") {
				BuildingWithResources building = (BuildingWithResources)mainJFrame.getDrawMap().getSelectedMapTile().getBuilding();
				building.levelUp(GameCoreController.resourceController);
			}			
		} catch (ClassCastException e) {
		}
	}
}
