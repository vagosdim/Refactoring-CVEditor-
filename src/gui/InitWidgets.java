package gui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class InitWidgets {
	
	private CvCommonFunctions cvCommonFunctions = new CvCommonFunctions();
	
	public JButton createButton(JPanel panel, String buttonLabel,
		int x, int y, int width, int height,Integer vTextPos,Integer hTextPos,String toolTip) {
		JButton button = new JButton(buttonLabel);
		button.setBounds(x, y, width, height);
		button.setVerticalAlignment(vTextPos);
		button.setHorizontalAlignment(hTextPos);
		button.setToolTipText(toolTip);
		panel.add(button);
		return button;
	}
	
	public JButton createButton(JPanel panel, String buttonLabel,
		int x, int y, int width, int height) {
		JButton button = new JButton(buttonLabel);
		button.setBounds(x, y, width, height);
		panel.add(button);
		return button;
	}
	
	public JCheckBox createJCheckBox(JPanel panel, String checkBoxLabel,
		int x, int y, int width, int height) {
		JCheckBox checkBox = new JCheckBox(checkBoxLabel);
		checkBox.setBounds(x, y, width, height);
		panel.add(checkBox);
		return checkBox;
	}
	
	public JTextField createJtextField(JPanel panel, String textFieldLabel,
		int x, int y, int width, int height,int columns) {
		JTextField textField = new JTextField(textFieldLabel);
		textField.setBounds(x, y, width, height);
		panel.add(textField);
		textField.setColumns(columns);
		return textField;
	}
	
	public JLabel createJLabel(JPanel panel, String labelName,
		int x, int y, int width, int height) {
		JLabel label = new JLabel(labelName);
		label.setBounds(x, y, width, height);
		panel.add(label);
		return label;
	}
	
	public JLabel createJLabel(JPanel panel, String labelName,
			int x, int y, int width, int height,Integer vTextPos,Integer hTextPos,Color color) {
		JLabel label = new JLabel(labelName);
		label.setBounds(x, y, width, height);
		label.setForeground(color);
		label.setVerticalAlignment(vTextPos);
		label.setHorizontalAlignment(hTextPos);
		panel.add(label);
		return label;
	}
	
	public JTextArea createJTextArea(JPanel panel,
		int x, int y, int width, int height) {
		JTextArea textArea = new JTextArea();
		textArea.setBounds(x, y, width, height);
		panel.add(textArea);
		return textArea;
	}
	
	public JPanel createJPanel(JPanel panel,
		int x, int y, int width, int height, Color color) {
		JPanel panel1 = new JPanel();
		panel1.setBounds(x, y, width, height);
		panel.add(panel1);
		panel1.setBackground(color);
		return panel1;
	}
	
	public JFrame createJFrame() {
		JFrame frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setTitle("Curriculum Vitae (CV) Editor");
		frame.setResizable(false);
		frame.setBounds(400, 150, 725, 350);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		cvCommonFunctions.onExit(frame, false);
		return frame;
	}
}
