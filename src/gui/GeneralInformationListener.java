package gui;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GeneralInformationListener implements ActionListener{
	
	private String[] generalInformation = new String[5];
	private CvCommonFunctions cvCommonFunctions = new CvCommonFunctions();
	
    public GeneralInformationListener(String[] generalInfo) {
		this.generalInformation = generalInfo;
	}
    
	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame generalInformationWindow = cvCommonFunctions.createFrame("General Information.", 600, 250, 400, 400, false);
		generalInformationWindow.getContentPane().setLayout(null);
		cvCommonFunctions.onExit(generalInformationWindow, true);
		
		JLabel labelName = cvCommonFunctions.createLabel("Name : ", 10, 10, 370, 20, 0);
		generalInformationWindow.getContentPane().add(labelName);
						
		JTextField  nameField = cvCommonFunctions.createTextField(10, 30, 370, 20);		
		generalInformationWindow.getContentPane().add(nameField);
		
		JLabel labelAddress = cvCommonFunctions.createLabel("Address : ", 10, 60, 370, 20, 0);
		generalInformationWindow.getContentPane().add(labelAddress);
		
		JTextField  addressField = cvCommonFunctions.createTextField(10, 80, 370, 20);	
		generalInformationWindow.getContentPane().add(addressField);
		
		JLabel labelHome = cvCommonFunctions.createLabel("Home Telephone : ", 10, 110, 370, 20, 0);
		generalInformationWindow.getContentPane().add(labelHome);
		
		JTextField  homeField = cvCommonFunctions.createTextField(10, 130, 370, 20);
		generalInformationWindow.getContentPane().add(homeField);
		
		JLabel labelMobile = cvCommonFunctions.createLabel("Mobile : ", 10, 160, 370, 20, 0);
		generalInformationWindow.getContentPane().add(labelMobile);
		
		JTextField  mobileField = cvCommonFunctions.createTextField(10, 180, 370, 20);	
		generalInformationWindow.getContentPane().add(mobileField);
		
		JLabel labelEmail = cvCommonFunctions.createLabel("Email : ", 10, 210, 370, 20, 0);
		generalInformationWindow.getContentPane().add(labelEmail);
		
		JTextField  emailField = cvCommonFunctions.createTextField(10, 230, 370, 20);
		generalInformationWindow.getContentPane().add(emailField);
		
		
		nameField.setText(generalInformation[0]);
		addressField.setText(generalInformation[1]);
		homeField.setText(generalInformation[2]);
		mobileField.setText(generalInformation[3]);
		emailField.setText(generalInformation[4]);
		
		JButton save = new JButton("Save");
		save.setBounds(300, 300, 80 , 50);
		generalInformationWindow.getContentPane().add(save);
		
		save.addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent e) {
				generalInformation[0] = nameField.getText();
				generalInformation[1] = addressField.getText();
				generalInformation[2] = homeField.getText();
				generalInformation[3] = mobileField.getText();
				generalInformation[4] = emailField.getText();
				
			}
			
		});
		
		generalInformationWindow.setVisible(true);
		
	}
	
}
