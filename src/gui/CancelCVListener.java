package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class CancelCVListener implements ActionListener{

	private JFrame frame;
	public CancelCVListener(JFrame frame) {
		this.frame = frame;
	}
	
	public void actionPerformed(ActionEvent e) {
		frame.setVisible(false);
		CVMainJFrameWindow.makeVisible();
	}
}
