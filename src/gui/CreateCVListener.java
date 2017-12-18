package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import data.manage.CVTemplate;

public class CreateCVListener implements ActionListener{
	
	private JFrame cvMainWindow;
	private JCheckBox checkBoxChron;
	private JCheckBox checkBoxFunc;
	private JCheckBox checkBoxComb;
	private ChronologicalJFrameWindow chronologicalJFrameWindow = new ChronologicalJFrameWindow(cvMainWindow);
	
	 public CreateCVListener(JFrame cvMainWindow,JCheckBox func,JCheckBox chron,JCheckBox comb) {
		this.cvMainWindow = cvMainWindow;
		this.checkBoxFunc = func;
		this.checkBoxChron = chron;
		this.checkBoxComb = comb;
	}
	
	public void actionPerformed(ActionEvent e) {
		if ( checkBoxChron.isSelected()) {
			cvMainWindow.setVisible(false);
			CVTemplate cvtemplate = new CVTemplate(null,null,null,null,null);
			chronologicalJFrameWindow.makeVisible(cvtemplate);
		} else if ( checkBoxFunc.isSelected() ){
			cvMainWindow.setVisible(false);
			CVTemplate cvtemplate = new CVTemplate(null,null,null,null,null);
			FunctionalJFrameWindow.makeVisible(cvtemplate);
		} else if (checkBoxComb.isSelected() ) {
			cvMainWindow.setVisible(false);
			CVTemplate cvtemplate = new CVTemplate(null,null,null,null,null);
			CombinedJFrameWindow.makeVisible(cvtemplate);
		} else {
			JOptionPane.showMessageDialog(null, "Please select a CV first. :)");
		}
	}
}