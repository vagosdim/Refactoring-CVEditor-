package gui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import data.manage.CVTemplate;
import data.manage.Database;
import input.manage.InputSystemLatex;
import input.manage.InputSystemTxt;
import output.manage.OutputSystemLatex;
import output.manage.OutputSystemTxt;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

public class CVMainJFrameWindow{
	/*
	 * 		Creates the main Window and calls the event Listener of the checkboxes and "Create CV" button.
	 */
	static JFrame cvMainWindow;
	public static void main(String[] args) {

		ChronologicalJFrameWindow chronologicalJFrameWindow = new ChronologicalJFrameWindow(cvMainWindow);
		FunctionalJFrameWindow functionalJFrameWindow = new FunctionalJFrameWindow(cvMainWindow);
		CombinedJFrameWindow combinedJFrameWindow = new CombinedJFrameWindow(cvMainWindow);
		CompareOrMergeTwoCVsJFrameWindow compareTwoCVsJFrameWindow = new CompareOrMergeTwoCVsJFrameWindow(cvMainWindow);

		cvMainWindow = createFrame();
		onExit(cvMainWindow, false);
		JPanel panel = new JPanel();
		panel.setBounds(new Rectangle(500, 500, 500, 500));
		panel.setSize(756, 568);
		cvMainWindow.getContentPane().add(panel);
		panel.setLayout(null);

		JButton createCV = InitWidgets.createButton(panel,"Create a CV",562, 203, 129, 49,AbstractButton.CENTER, AbstractButton.LEADING, "Click This Button to create a new CV.");

		String labelName = "Hello. Please check what kind of CV you want to create and click on \"Create a CV\".";
		JLabel lblCheck = InitWidgets.createJLabel(panel, labelName, 10,11,712,49,SwingConstants.CENTER,SwingConstants.LEFT,Color.white);

		JPanel panel1 = InitWidgets.createJPanel(panel, 0, 0, 795, 56, Color.DARK_GRAY);
		JPanel panel2 = InitWidgets.createJPanel(panel, 0, 303, 808, 69, Color.DARK_GRAY);
		JPanel panel3 = InitWidgets.createJPanel(panel, 0, 494, 795, 86, Color.DARK_GRAY);
		
		JCheckBox chckbxChronological = InitWidgets.createJCheckBox(panel, "Chronological",20, 127, 167, 23 );
		JCheckBox chckbxFunctional = InitWidgets.createJCheckBox(panel, "Functional",20, 70, 167, 23);
		JCheckBox chckbxCombined = InitWidgets.createJCheckBox(panel, "Combined",20, 190, 167, 23 );
		
		JLabel labelChronological = InitWidgets.createJLabel(panel, "Create a Chronological CV",30, 157, 211, 14);
		JLabel labelFunctional = InitWidgets.createJLabel(panel, "Create a Functional CV",30, 100, 129, 14);
		JLabel labelCombined = InitWidgets.createJLabel(panel, "Create a Combined CV",30, 220, 129, 14);
		JLabel lblYouCanSave = InitWidgets.createJLabel(panel,"Here, you can edit an existing CV in a number of ways.",0, 303, 773, 69,SwingConstants.CENTER, SwingConstants.CENTER,Color.white); 
		lblYouCanSave.setBackground(Color.DARK_GRAY);
	
		String toolTip = "Click This Button to open an existing CV.";
		JButton btnOpenACv = InitWidgets.createButton(panel, "Open a CV",70, 407, 143, 76,SwingConstants.CENTER,SwingConstants.LEADING, toolTip);
		toolTip = "Click This Button to delete a CV.";
		JButton btnDeleteACv = InitWidgets.createButton(panel, "Delete a CV",324, 407, 143, 76,SwingConstants.CENTER,SwingConstants.LEADING,toolTip);
		toolTip ="Click This Button to compare or mergge two existing CV's.";
		JButton btnCompareTwoCvs = InitWidgets.createButton(panel, "Compare two CV's",567, 407, 143, 76, SwingConstants.CENTER,SwingConstants.LEADING, toolTip);
		
		eventListenerMainWindow(cvMainWindow, chckbxChronological, labelChronological, chckbxFunctional, labelFunctional,
								chckbxCombined, labelCombined, createCV, btnOpenACv, btnDeleteACv, btnCompareTwoCvs);
	}
	
	/*		These Methods give values to the Frame, Buttons, Labels created in the main Function
	 *
	 */

	public static JFrame createFrame(){
		JFrame cvMainWindow =new JFrame("Curriculum Vitae (CV) Creator");
		cvMainWindow.setResizable(false);
		cvMainWindow.setBounds(400, 150, 800, 600);
		cvMainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cvMainWindow.setVisible(true);
		return cvMainWindow;
	}

	public static void onExit(JFrame frame, boolean dispose){
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent){
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "title" , 0) == JOptionPane.YES_OPTION){
					if (dispose){
						frame.setDefaultCloseOperation(2);
					} else {
						frame.setDefaultCloseOperation(3);
					}
				} else {
					frame.setDefaultCloseOperation(0);
				}
			}
		});
	}

	public static void eventListenerMainWindow(JFrame cvMainWindow, JCheckBox chckbxChronological, JLabel labelChronological,
												JCheckBox chckbxFunctional, JLabel labelFunctional, JCheckBox chckbxCombined,
												JLabel labelCombined, JButton createCV, JButton btnOpenACv, JButton btnDeleteACv,
												JButton btnCompareTwoCvs){
//---------------------------- EVENT LISTENERS-----------------------

		btnOpenACv.addActionListener(new OpenCVListener(cvMainWindow));
		btnDeleteACv.addActionListener(new DeleteCVListener(cvMainWindow));
		
		btnCompareTwoCvs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CompareOrMergeTwoCVsJFrameWindow.makeVisible();
			}
		});

		// If Chronological CV is selected disable others
		chckbxChronological.addActionListener(new CheckBoxCVMainListener(chckbxChronological, chckbxFunctional, chckbxCombined,
																		 labelFunctional, labelCombined));

		// If Functional CV is selected disable others
		chckbxFunctional.addActionListener(new CheckBoxCVMainListener(chckbxFunctional, chckbxChronological, chckbxCombined,
				 													  labelChronological, labelCombined));
			
		// If Combined CV is selected disable others
		chckbxCombined.addActionListener(new CheckBoxCVMainListener(chckbxCombined, chckbxChronological, chckbxFunctional,
				 													labelFunctional, labelChronological));
			
		/* Event Handler for create CV button, checks if CV is selected and proceeds to create the next frame
		 *	or displays a warning message
		 */
		createCV.addActionListener(new CreateCVListener(cvMainWindow, chckbxFunctional, chckbxChronological, chckbxCombined));
	}

	public static void makeVisible(){
		cvMainWindow.setVisible(true);
	}
}