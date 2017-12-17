package gui;

import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.text.ParseException;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SaveCVListener implements ActionListener{
	
	private JCheckBox checkBoxTxt;
	private JCheckBox checkBoxTex;
	private String type;
	
	public SaveCVListener(JCheckBox checkBoxTxt, JCheckBox checkBoxTex,String type) {
		this.checkBoxTxt = checkBoxTxt;
		this.checkBoxTex = checkBoxTex;
		this.type = type;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			if (checkBoxTxt.isSelected() && checkBoxTex.isSelected() ){
				abstractSaveCV(0,type);
			}else if (checkBoxTxt.isSelected()){
				abstractSaveCV(1, type);
			}else if (checkBoxTex.isSelected()){
				abstractSaveCV(2, type);
			}else {
				JOptionPane.showMessageDialog(null, "Please select a type to output first, then save.");
			}
				
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	}

	private void abstractSaveCV(int x, String type) throws ParseException {
		if(type.equals("Functional")) {
			FunctionalJFrameWindow.saveCV(x);
		}else if(type.equals("Chronological")) {
			ChronologicalJFrameWindow.saveCV(x);
		}else {
			CombinedJFrameWindow.saveCV(x);
		}
	}
}
