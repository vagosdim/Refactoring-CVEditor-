package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

public class CheckBoxCVMainListener implements ActionListener{

	private JCheckBox c1;
	private JCheckBox c2;
	private JCheckBox c3;
	private JLabel l1;
	private JLabel l2;
	
	public CheckBoxCVMainListener(JCheckBox c1, JCheckBox c2, JCheckBox c3, JLabel l1, JLabel l2) {
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.l1 = l1;
		this.l2 = l2;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (c1.isSelected()){
			c2.setEnabled(false);
			l1.setEnabled(false);
			c3.setEnabled(false);
			l2.setEnabled(false);
		}else{
			c2.setEnabled(true);
			l1.setEnabled(true);
			c3.setEnabled(true);
			l2.setEnabled(true);
		}
	}
}
