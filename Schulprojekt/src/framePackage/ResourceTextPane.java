package framePackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextPane;
import javax.swing.Timer;

import gameCore.GameCoreController;

public class ResourceTextPane extends JTextPane {
	private static final long serialVersionUID = 8637688328571070805L;
	
	private static UpdateToolTip utt;
	private ResourceTextPane self = this;
	private static Timer t;
	private class UpdateToolTip implements Runnable {
		
		@Override
		public void run() {
			t = new Timer(40, new UpdateAction());
			if(t.isRunning()) {
				t.restart();
			} else {
				t.start();
			}
			t.setRepeats(true);
		}
		private class UpdateAction implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent evt) {
				self.setText(GameCoreController.resourceController.toString());
			}
			
		}

	}
	public ResourceTextPane() {
		utt = new UpdateToolTip();
		utt.run();
	}

}
