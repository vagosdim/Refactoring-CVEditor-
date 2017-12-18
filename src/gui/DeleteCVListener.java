package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DeleteCVListener implements ActionListener {

	private JFrame cvMainWindow;
	
	public DeleteCVListener(JFrame cvMainWindow) {
		this.cvMainWindow = cvMainWindow;
	}
	
	public void actionPerformed(ActionEvent e) {
		JFileChooser deleteCvFileChooser = new JFileChooser();
		deleteCvFileChooser.setCurrentDirectory(new java.io.File("."));
		deleteCvFileChooser.setDialogTitle("Please select the CV file.");
		if(deleteCvFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
		    String deleteCvFilePath = deleteCvFileChooser.getSelectedFile().toString();
		    Path deleteCvFilePath2 = Paths.get(deleteCvFilePath);
		    try {
				Files.delete(deleteCvFilePath2);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		    JOptionPane.showMessageDialog(null, deleteCvFilePath, "Directory of deleted CV", JOptionPane.INFORMATION_MESSAGE);
		    cvMainWindow.setVisible(true);
		}
	}
}
